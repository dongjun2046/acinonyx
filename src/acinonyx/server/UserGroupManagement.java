package acinonyx.server;

import acinonyx.db.OpsRequestsInfoStore;
import java.io.IOException;
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

import acinonyx.conf.ReadConfiguration;
import acinonyx.db.OpsRequestsInfoStore;

/**
 * Servlet implementation class UserGroupManagement
 */
@WebServlet("/usermgmnt")
public class UserGroupManagement extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserGroupManagement() {
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
		String requestor = (String) session.getAttribute("wings-logged-username");
		OpsRequestsInfoStore oris = new OpsRequestsInfoStore();
		String requestor_email = oris.getEmail(requestor);
		OpsRequestsInfoStore doris = new OpsRequestsInfoStore();
		Connection conn = doris.getDBConnection();
		String tid = doris.getTicketID(conn);

		List<String> ticketData = new ArrayList<String>();
		ticketData.add("Create the users, group and folders");

		List<String> msgList_1 = new ArrayList<String>();
		List<String> msgList_2 = new ArrayList<String>();
		List<String> msgList_3 = new ArrayList<String>();

		String userNames = request.getParameter("unames").trim();
		String groupNames = request.getParameter("gnames").trim();

		if (!groupNames.matches("[a-z0-9_-]+") || groupNames.length() == 0 || groupNames.equalsIgnoreCase("root")) {
			groupNames = "users";
		}
		String foldersList = request.getParameter("os_layout").trim();

		ReadConfiguration readConf = new ReadConfiguration();
		String hostNames = readConf.getUagHostname();

		doris.newRequest(conn, tid, "edgenode", requestor, doris.getTimeStamp(), doris.getTimeStamp(), ticketData,
				"requested");

		if (userNames == "" && groupNames == "" && hostNames == "") {
			msgList_2.add("Usernames and GroupName details not provided..!");
		} else {
			ticketData.add("Usernames : " + userNames);
			ticketData.add("Groupnames : " + groupNames);
			ticketData.add("Hostnames : " + hostNames);
			doris.updateTicketData(conn, tid, ticketData);

			List<String> hostNameList = Arrays.asList(hostNames.split(","));
			List<String> userList = Arrays.asList(userNames.split(","));

			List<String> finalUserList = new ArrayList<String>();
			for (String uName : userList) {
				if (uName.matches("[a-z0-9_]+") && uName.length() > 0) {
					finalUserList.add(uName);
				}
			}
			if (finalUserList.size() == 0)
				finalUserList.add("users");

			UserGroupCreation ugc = new UserGroupCreation();
			FolderCreation fc = new FolderCreation();

			doris.updateRequest(conn, tid, doris.getTimeStamp(), "wip");

			for (String hostName : hostNameList) {
				msgList_1.add("Edge Node : " + hostName);
				msgList_2 = ugc.createUsersGroup(tid, userList, groupNames, hostName,requestor_email);
				msgList_3 = fc.createFolders(tid, foldersList, finalUserList.get(finalUserList.size() - 1), groupNames,
						hostName);
				msgList_1.addAll(msgList_2);
				msgList_1.addAll(msgList_3);
			}

		}
		doris.updateRequest(conn, tid, doris.getTimeStamp(), "closed");
		doris.closeDBConnection();
		request.setAttribute("msg", msgList_1);
		request.getRequestDispatcher("/msg.jsp").forward(request, response);
	}

}
