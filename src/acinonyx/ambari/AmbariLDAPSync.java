package acinonyx.ambari;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import acinonyx.ambari.AmbariReadOnly;
import acinonyx.db.OpsRequestsInfoStore;

/**
 * Servlet implementation class AmbariLDAPSync
 */
@WebServlet("/AmbariLDAPSync")
public class AmbariLDAPSync extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AmbariLDAPSync() {
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
		// TODO Auto-generated method stub
		// doGet(request, response);
		HttpSession session = request.getSession();
		String requestor = (String) session.getAttribute("wings-logged-username");
		OpsRequestsInfoStore doris = new OpsRequestsInfoStore();
		Connection conn = doris.getDBConnection();
		String tid = doris.getTicketID(conn);

		List<String> ticketData = new ArrayList<String>();
		ticketData.add("Provide readonly access to Ambari Server");
		AmbariReadOnly amo = new AmbariReadOnly();
		PrintWriter out = response.getWriter();
		String unames = request.getParameter("unames").trim();
		ticketData.add(unames);

		doris.newRequest(conn, tid, "ambariui", requestor, doris.getTimeStamp(), doris.getTimeStamp(), ticketData,
				"requested");
		doris.updateRequest(conn, tid, doris.getTimeStamp(), "wip");

		boolean status = amo.execute(tid, unames);

		out.println("<!DOCTYPE html>");
		out.println("<html lang=\"en\">");
		out.println("<head>");
		out.println("<title>Wings</title>");
		out.println("<meta charset=\"utf-8\">");
		out.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">");
		out.println("<link rel=\"stylesheet\" href=\"./bootstrap/css/bootstrap.min.css\">");
		out.println("<script src=\"./custom/js/jquery-3.1.1.min.js\"></script>");
		out.println("<script src=\"./bootstrap/js/bootstrap.min.js\"></script>");
		out.println("<link rel=\"stylesheet\" href=\"./custom/css/custom.css\">");
		out.println("</head>");
		out.println("<body>");

		out.println("<script type=\"text/javascript\">");
		if (status) {
			doris.updateRequest(conn, tid, doris.getTimeStamp(), "closed");
			out.println("alert('Provided access to the user : " + unames + "')");
		} else {
			doris.updateRequest(conn, tid, doris.getTimeStamp(), "error");
			out.println("alert('Failed to provide access to the user : " + unames + "')");
		}
		doris.closeDBConnection();
		out.println("window.location.href=\"ambari.jsp\"");
		out.println("</script>");

		out.println("</body>");
		out.println("</html>");
		// response.sendRedirect("./ambserver.jsp");
	}

}
