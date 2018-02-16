package acinonyx.hdfs;

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

/**
 * Servlet implementation class ProcessHDFS
 */
@WebServlet("/processhdfs")
public class ProcessHDFS extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ProcessHDFS() {
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

		HttpSession session = request.getSession();
		String requestor = (String) session.getAttribute("wings-logged-username");

		List<String> list = new ArrayList<String>();
		ProcessHDFSLayout phl = new ProcessHDFSLayout();
		String hdfslayout = request.getParameter("hdfs_layout");
		hdfslayout = hdfslayout.trim();

		OpsRequestsInfoStore doris = new OpsRequestsInfoStore();
		Connection conn = doris.getDBConnection();
		String tid = doris.getTicketID(conn);

		List<String> givenList = new ArrayList<String>();
		givenList.add("");
		doris.newRequest(conn, tid, "hdfs", requestor, doris.getTimeStamp(), doris.getTimeStamp(), givenList,
				"requested");

		if (hdfslayout != "" || hdfslayout != " " || hdfslayout != null) {
			String[] folders = hdfslayout.split("\\n");
			for (String folder : folders) {
				givenList.add(folder.trim());
			}
			doris.updateTicketData(conn, tid, givenList);

			doris.updateRequest(conn, tid, doris.getTimeStamp(), "wip");
			list = phl.processHDFSLayout(tid, hdfslayout);
			doris.updateRequest(conn, tid, doris.getTimeStamp(), "closed");
		} else {
			doris.updateRequest(conn, tid, doris.getTimeStamp(), "error");
			list.add("Invalid/Empty Entries..!");
		}
		doris.closeDBConnection();

		request.setAttribute("msg", list);
		request.getRequestDispatcher("/msg.jsp").forward(request, response);
	}

}
