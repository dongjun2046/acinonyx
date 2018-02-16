package acinonyx.ambari;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import acinonyx.db.OpsRequestsInfoStore;

/**
 * Servlet implementation class ProcessViews
 */
@WebServlet("/processviews")
public class ProcessViews extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ProcessViews() {
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

		List<String> givenList = new ArrayList<String>();
		givenList.add("Provide access to Ambari Views");

		String[] views = request.getParameterValues("views");
		String unameview = request.getParameter("unameview");
		unameview = unameview.trim();
		doris.newRequest(conn, tid, "ambari-views", requestor, doris.getTimeStamp(), doris.getTimeStamp(), givenList,
				"raised");

		List<String> list = new ArrayList<String>();
		PrintWriter out = response.getWriter();
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
		boolean status = false;

		if (views != null && views.length > 0 && unameview != null && unameview.length() > 0) {
			givenList.add("User ID : " + unameview);
			givenList.add("Views   : " + Arrays.asList(views));
			doris.updateRequest(conn, tid, doris.getTimeStamp(), "wip");
			doris.updateTicketData(conn, tid, givenList);

			list = Arrays.asList(views);
			AmbariViews avs = new AmbariViews();
			status = avs.execute(tid, unameview, list);
		} else {
			status = false;
		}
		if (status) {
			doris.updateRequest(conn, tid, doris.getTimeStamp(), "closed");
			out.println("alert('Provided access to the user : " + unameview + "')");
		} else {
			doris.updateRequest(conn, tid, doris.getTimeStamp(), "failed");
			out.println("alert('Failed to provide access')");
		}
		doris.closeDBConnection();
		out.println("window.location.href=\"ambari.jsp\"");
		out.println("</script>");

		out.println("</body>");
		out.println("</html>");
	}

}
