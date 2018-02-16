package acinonyx.hive;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import acinonyx.db.OpsRequestsInfoStore;

//import java.io.PrintWriter;

/**
 * Servlet implementation class ProcessHiveQuery
 */
@WebServlet({ "/ProcessHiveQuery", "/processHiveQuery", "/processhivequery" })
public class ProcessHiveQuery extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ProcessHiveQuery() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		String query = request.getParameter("query").trim();
		String requestor = (String) session.getAttribute("wings-logged-username");
		OpsRequestsInfoStore doris = new OpsRequestsInfoStore();
		Connection conn = doris.getDBConnection();
		String tid = doris.getTicketID(conn);

		List<String> rec_messages = new ArrayList<String>();
		List<String> sen_messages = new ArrayList<String>();
		List<String> ticketData = new ArrayList<String>();

		ticketData.add("Process the hive request : ");
		doris.newRequest(conn, tid, "hive", requestor, doris.getTimeStamp(), doris.getTimeStamp(), ticketData,
				"requested");
		doris.updateRequest(conn, tid, doris.getTimeStamp(), "wip");
		if (query != null) {
			ticketData.add(query);
			doris.updateTicketData(conn, tid, ticketData);
			if ((query.toLowerCase()).startsWith("create table") || (query.toLowerCase()).startsWith("create external")
					|| (query.toLowerCase()).startsWith("create temporary")) {

				HiveActions hiveAction = new HiveActions();

				rec_messages = hiveAction.executeQuery(request.getParameter("dbname"), request.getParameter("query"));
				sen_messages.addAll(rec_messages);
				doris.updateRequest(conn, tid, doris.getTimeStamp(), "closed");

			} else {
				sen_messages.add("Invalid query : ");
				sen_messages.add(query);
				sen_messages.add(
						"Ensure query begins with - 'create table' or 'create external table' or 'create temperory table' only");
				doris.updateRequest(conn, tid, doris.getTimeStamp(), "failed");
			}
		} else {
			doris.updateRequest(conn, tid, doris.getTimeStamp(), "failed");
			sen_messages.add("Specify your script..!");
		}

		request.setAttribute("msg", sen_messages);
		request.getRequestDispatcher("/msg.jsp").forward(request, response);
	}
}
