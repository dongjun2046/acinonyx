package acinonyx.common;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import acinonyx.db.OpsRequestsInfoStore;

/**
 * Servlet implementation class LoadDashboard
 */
@WebServlet("/load_dashboard")
public class LoadDashboard extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private OpsRequestsInfoStore doris;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoadDashboard() {
		super();
		doris = new OpsRequestsInfoStore();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		int userExists;
		String user;
		HttpSession session = request.getSession();

		if (session.getAttribute("wings-logged-logstate") == null
				|| session.getAttribute("wings-logged-logstate") != "loggedin") {
			response.sendRedirect("login.jsp");
		} else {

			user = session.getAttribute("wings-logged-username").toString();
			userExists = (int) session.getAttribute("wings-logged-usertype");

			RequestDispatcher rd = request.getRequestDispatcher("");
			// .getRequestDispacher("servlet2").forward(request,response);
			request.setAttribute("ticketDetails", doris.getAllTicketDetails(userExists, user));

			RequestDispatcher view = request.getRequestDispatcher("main.jsp");
			view.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
