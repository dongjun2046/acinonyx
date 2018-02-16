package acinonyx.db;

import java.sql.Connection;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TestConnection {
	public static void main(String[] args) {
		OpsRequestsInfoStore doris = new OpsRequestsInfoStore();
		Connection conn = doris.getDBConnection();
		String tid = doris.getTicketID(conn);

		LocalDateTime timePoint = LocalDateTime.now();
		Timestamp req_start = Timestamp.valueOf(timePoint);

		List<Integer> list = new ArrayList<Integer>();

		// System.out.println(list.toString());
		// doris.newRequest(conn, tid, "ambari", "admin", req_start, req_start,
		// list, "Raised");
		doris.getAllTicketDetails(1, "hemant");
		doris.closeDBConnection();

	}
}
