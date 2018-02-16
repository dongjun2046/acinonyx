package acinonyx.kafka;

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

import acinonyx.ambari.AmbariViews;
import acinonyx.db.OpsRequestsInfoStore;

/**
 * Servlet implementation class KafkaTopicAcl
 */
@WebServlet({ "/KafkaTopicAcl", "/kafkatopicacl" })
public class KafkaTopicAcl extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public KafkaTopicAcl() {
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
		OpsRequestsInfoStore doris = new OpsRequestsInfoStore();
		Connection conn = doris.getDBConnection();

		String tid = doris.getTicketID(conn);
		List<String> ticketData = new ArrayList<String>();
		ticketData.add("Create the Kafka Topic process acls - ");

		doris.newRequest(conn, tid, "kafka", requestor, doris.getTimeStamp(), doris.getTimeStamp(), ticketData,
				"requested");

		String topicName = request.getParameter("topicname");
		String[] perms = request.getParameterValues("perms");
		int partition = Integer.parseInt(request.getParameter("partition"));
		int repl = Integer.parseInt(request.getParameter("repl"));
		String princ = request.getParameter("princ");
		List<String> permsList = new ArrayList<String>();
		permsList = Arrays.asList(perms);

		ticketData.add("Topic Name : " + topicName);
		ticketData.add("Partitions : " + partition);
		ticketData.add("Replication : " + repl);
		ticketData.add("Principal : " + princ);
		ticketData.add("Permissions : " + permsList.toString());

		doris.updateTicketData(conn, tid, ticketData);

		// System.out.println(topicName + " " + partition + " " + repl + " " +
		// princ + " " + permsList);
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
		out.println("<body bgcolor=\"#fff\">");
		out.println("<script type=\"text/javascript\">");
		boolean status = false;

		KafkaTopicAclCreation kac = new KafkaTopicAclCreation();
		doris.updateRequest(conn, tid, doris.getTimeStamp(), "wip");
		status = kac.createTopicWithACL(tid, topicName, partition, repl, princ, permsList);
		if (status) {
			doris.updateRequest(conn, tid, doris.getTimeStamp(), "closed");
			out.println("alert('Created topic : " + topicName + " and provided access to the user : " + princ + "')");
		} else {
			doris.updateRequest(conn, tid, doris.getTimeStamp(), "failed");
			out.println("alert('Issue encountered while creating the topic : " + topicName + " ')");
		}
		doris.closeDBConnection();
		out.println("window.location.href=\"kafka.jsp\"");
		out.println("</script>");

		out.println("</body>");
		out.println("</html>");

	}

}
