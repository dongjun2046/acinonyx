package acinonyx.ranger.hdfs;

import java.io.IOException;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class fetchRanger
 */
@WebServlet("/fetchRanger")
public class fetchRanger extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public fetchRanger() {
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
		// response.getWriter().append("Served at:
		// ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// doGet(request, response);
		// curl -u admin:admin -H "Content-type:application/json" -X GET
		// "http://192.168.131.129:6080/service/public/api/policy?resourceName=/org/abc"
		String resource = request.getParameter("resource");
		String link = "";
		if (resource.length() == 0)
			link = "http://sgscvaiu0304.inedc.corpintra.net:6080/service/public/api/policy?resourceName=/org/";
		else
			link = "http://sgscvaiu0304.inedc.corpintra.net:6080/service/public/api/policy?resourceName=" + resource;

		RangerHDFS rhdfs = new RangerHDFS();
		List<PolicyDetails> policyDetailsList = rhdfs.fetchPolicies(link);
		request.setAttribute("policyDetailsList", policyDetailsList);
		request.getRequestDispatcher("ranger-hive.jsp").forward(request, response);
		// view.forward(request, response);
	}
}
