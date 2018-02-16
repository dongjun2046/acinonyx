package acinonyx.common;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.nio.file.Path;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import acinonyx.conf.ReadConfiguration;
import acinonyx.db.OpsRequestsInfoStore;
import acinonyx.db.OpsRequestsPOJO;

/**
 * Servlet implementation class login_validate
 */
@WebServlet("/login_validate")
public class LoginValidate extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private OpsRequestsInfoStore doris;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginValidate() {
		super();
		doris = new OpsRequestsInfoStore();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub

	}

	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		final Logger web_log = Logger.getLogger(LoginValidate.class);
		ReadConfiguration readConf = new ReadConfiguration();
		CheckUser cu = new CheckUser();
		PropertyConfigurator.configure(readConf.getLog4j());

		String user = request.getParameter("username");
		String pwd = request.getParameter("password");

		int userExists = cu.checkIfUserExists(user, pwd);
		if (userExists == 0 || userExists == 1) {

			HttpSession session = request.getSession();
			
			session.setAttribute("wings-logged-cluster-name", readConf.getAmbariClusterName());
			session.setAttribute("wings-logged-username", user);
			session.setAttribute("wings-logged-usertype", userExists);
			session.setAttribute("wings-logged-logstate", "loggedin");
			session.setAttribute("wings-logged-seesionid", session.getId());
			session.setAttribute("wings-workdir", readConf.getWorkDir());
			web_log.info("Successfully logged in as : " + user + " ,Session Id : " + session.getId());
			session.setMaxInactiveInterval(30 * 60);
			Path path = Paths.get(session.getAttribute("wings-workdir") + session.getId());

			if (!Files.exists(path)) {
				try {
					Files.createDirectories(path);
				} catch (IOException exception) {
					web_log.error("For the session - " + session.getId() + "\n" + exception);
				}
			}

			RequestDispatcher view = request.getRequestDispatcher("load_dashboard");
			view.forward(request, response);

		} else if (userExists == 999) {
			web_log.error("Login Failed with given credentials");
			response.sendRedirect("login.jsp?message=failure");
		} else if (userExists == -9) {
			web_log.error("Unauthorized Access by user - " + user);
			response.sendRedirect("login.jsp?message=noaccess");
		} else {
			web_log.error("Unknown error while logging in");
			response.sendRedirect("login.jsp?message=error");
		}
	}
}
