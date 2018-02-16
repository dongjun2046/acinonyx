package acinonyx.common;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import acinonyx.db.OpsRequestsInfoStore;

/**
 * Servlet implementation class LoadTicketDetails
 */
@WebServlet("/loadticketdetails")
public class LoadTicketDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public OpsRequestsInfoStore doris;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoadTicketDetails() {
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
		String ticket_id = request.getParameter("ticketid");
		RequestDispatcher rd = request.getRequestDispatcher("");
		PrintWriter out = response.getWriter();
		out.println("\n" + ticket_id);
		doris = new OpsRequestsInfoStore();

		request.setAttribute("ticketDetails", doris.getTicketDetails(ticket_id));

		RequestDispatcher view = request.getRequestDispatcher("ticketdetails.jsp");
		view.forward(request, response);
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
