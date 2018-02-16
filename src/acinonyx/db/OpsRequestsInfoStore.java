package acinonyx.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import acinonyx.conf.ReadConfiguration;

public class OpsRequestsInfoStore {

	public Connection connection;
	public ReadConfiguration rc = new ReadConfiguration();
	public final Logger web_log = Logger.getLogger(OpsRequestsInfoStore.class);

	public Timestamp getTimeStamp() {
		LocalDateTime timePoint = LocalDateTime.now();
		Timestamp timestamp = Timestamp.valueOf(timePoint);
		return timestamp;
	}

	public Connection getDBConnection() {
		String dbhost = rc.getDbhost();
		String dbport = rc.getDbport();
		String dbname = rc.getDbname();
		String dbuser = rc.getDbuser();
		String dbpwd = rc.getDbpassword();
		String dburl = "jdbc:postgresql://" + dbhost + ":" + dbport + "/" + dbname;
		PropertyConfigurator.configure(rc.getLog4j());
		try {
			web_log.info("Connected to OpsRequestsInfoStore");
			this.connection = DriverManager.getConnection(dburl, dbuser, dbpwd);
		} catch (Exception e) {
			this.connection = null;
			web_log.error(e);
		}
		return this.connection;
	}

	public void closeDBConnection() {
		try {
			this.connection.close();
			web_log.info("Connection with OpsRequestsInfoStore closed");
		} catch (Exception e) {
			this.connection = null;
			web_log.error(e);
		}
	}

	public String getTicketID(Connection conn) {
		ReadConfiguration rc = new ReadConfiguration();
		PropertyConfigurator.configure(rc.getLog4j());

		int rowcount = 0;
		String ticketID = "";
		String query = "select NEXTVAL('ticket_sequence')";
		Statement stmt = null;
		ResultSet rs = null;
		if (conn == null) {
			ticketID = "HOPS000000";
		} else {
			try {
				stmt = conn.createStatement();
				rs = null;
				rs = stmt.executeQuery(query);
				while (rs.next()) {
					rowcount = rs.getInt(1);
				}
				ticketID = String.format("HOPS%06d", rowcount);
			} catch (Exception e) {
				ticketID = "HOPS000000";
				web_log.error("Issue while generating DBDPRequestID" + e);
			}
			try {
				rs.close();
				stmt.close();
			} catch (Exception e) {
				web_log.error("DBDPRequestID - Error while close statementset" + e);
			}
		}
		return ticketID;
	}

	// |-----------+-------------+-----------+-----------+---------+-------------+---------------|
	// | ticket_id | ticket_type | requestor | req_start | req_end | ticket_text
	// | ticket_status |
	// |-----------+-------------+-----------+-----------+---------+-------------+---------------|

	public boolean newRequest(Connection conn, String ticket_id, String ticket_type, String requestor,
			Timestamp req_start, Timestamp req_end, List<String> ticket_text, String ticket_status) {
		ReadConfiguration rc = new ReadConfiguration();
		PropertyConfigurator.configure(rc.getLog4j());
		boolean status = false;
		try {
			PreparedStatement stmt = conn.prepareStatement("insert into allrequests values(?,?,?,?,?,?,?)");
			stmt.setString(1, ticket_id);
			stmt.setString(2, ticket_type);
			stmt.setString(3, requestor);
			stmt.setTimestamp(4, req_start);
			stmt.setTimestamp(5, req_end);
			stmt.setString(6, ticket_text.toString());
			stmt.setString(7, ticket_status);

			stmt.executeUpdate();
			status = true;
			web_log.info("Raised a request with ID " + ticket_id);
		} catch (Exception e) {
			status = false;
			web_log.error("Error while logging the request " + ticket_id);
			web_log.error(e);
		}
		return status;
	}

	// |-----------+-------------+-----------+-----------+---------+-------------+---------------|
	// | ticket_id | ticket_type | requestor | req_start | req_end | ticket_text
	// | ticket_status |
	// |-----------+-------------+-----------+-----------+---------+-------------+---------------|
	public boolean updateRequest(Connection conn, String ticket_id, Timestamp req_end, String ticket_status) {
		ReadConfiguration rc = new ReadConfiguration();
		PropertyConfigurator.configure(rc.getLog4j());
		boolean status = false;
		try {
			PreparedStatement stmt = conn
					.prepareStatement("update allrequests set req_end = ?, ticket_status = ? where ticket_id = ?");
			stmt.setTimestamp(1, req_end);
			stmt.setString(2, ticket_status);
			stmt.setString(3, ticket_id);
			stmt.executeUpdate();
			status = true;
			web_log.info("Updated the request with ID " + ticket_id);
		} catch (Exception e) {
			status = false;
			web_log.error("Error while updating  the request " + ticket_id);
			web_log.error(e);
		}
		return status;
	}

	public boolean updateTicketData(Connection conn, String ticket_id, List<String> ticket_text) {
		ReadConfiguration rc = new ReadConfiguration();
		PropertyConfigurator.configure(rc.getLog4j());
		boolean status = false;
		try {
			PreparedStatement stmt = conn
					.prepareStatement("update allrequests set ticket_text = ? where ticket_id = ?");
			stmt.setString(1, ticket_text.toString());
			stmt.setString(2, ticket_id);
			stmt.executeUpdate();
			status = true;
			web_log.info("Updated the request with ID " + ticket_id);
		} catch (Exception e) {
			status = false;
			web_log.error("Error while updating  the request " + ticket_id);
			web_log.error(e);
		}
		return status;
	}

	public List<OpsRequestsPOJO> getAllTicketDetails(int userType, String userID) {

		ReadConfiguration rc = new ReadConfiguration();
		PropertyConfigurator.configure(rc.getLog4j());

		List<OpsRequestsPOJO> dorp = new ArrayList<OpsRequestsPOJO>();
		this.connection = getDBConnection();
		String query = "";

		// |-----------+-------------+-----------+-----------+---------+-------------+---------------|
		// | ticket_id | ticket_type | requestor | req_start | req_end |
		// ticket_text | ticket_status |
		// |-----------+-------------+-----------+-----------+---------+-------------+---------------|
		if (userType == 0)
			query = "select    ticket_id, " + "ticket_type, " + "requestor, " + "req_start, " + "req_end, "
					+ "ticket_text, " + "ticket_status " + "from allrequests";
		else
			query = "select    ticket_id, " + "ticket_type, " + "requestor, " + "req_start, " + "req_end, "
					+ "ticket_text, " + "ticket_status " + "from allrequests where requestor='" + userID + "'";
		web_log.info("Fetching the ticket details for the user - " + userID);
		// OpsRequestsPOJO dor = new OpsRequestsPOJO();
		try {
			Statement statement = this.connection.createStatement();
			ResultSet rs = statement.executeQuery(query);
			while (rs.next()) {
				OpsRequestsPOJO dor = new OpsRequestsPOJO();
				dor.setTicket_id(rs.getString(1));
				dor.setTicket_type(rs.getString(2));
				dor.setRequestor(rs.getString(3));
				dor.setReq_start(rs.getTimestamp(4));
				dor.setReq_end(rs.getTimestamp(5));
				dor.setTicket_text(rs.getString(6));
				dor.setTicket_status(rs.getString(7));
				dorp.add(dor);
				dor = null;
			}
		} catch (Exception e) {
			web_log.error("For User - " + userID + " Unable to fetch the records - " + e);
		}
		this.closeDBConnection();
		return dorp;
	}

	public List<LatestRequestsPOJO> getLatestTicketDetails(int userType, String userID) {

		ReadConfiguration rc = new ReadConfiguration();
		PropertyConfigurator.configure(rc.getLog4j());

		List<LatestRequestsPOJO> lrp = new ArrayList<LatestRequestsPOJO>();
		this.connection = getDBConnection();
		String query = "";

		if (userType == 0)
			query = "select 	count(*), " + "req_start::timestamp::date as req_date " + "from allrequests "
					+ "where req_start::timestamp::date < current_date " + "group by req_date " + "order by 2 asc "
					+ "limit 10";
		else
			query = "select 	count(*), " + "req_start::timestamp::date as req_date " + "from allrequests "
					+ "where req_start::timestamp::date < current_date " + " and requestor='" + userID + "' "
					+ "group by req_date " + "order by 2 asc " + "limit 10";

		web_log.info("Fetching the latest ticket details for the user - " + userID);

		return lrp;
	}

	public List<OpsRequestsPOJO> getTicketDetails(String ticket_id) {

		ReadConfiguration rc = new ReadConfiguration();
		PropertyConfigurator.configure(rc.getLog4j());
		List<OpsRequestsPOJO> dorp = new ArrayList<OpsRequestsPOJO>();
		this.connection = getDBConnection();
		String query = "";

		// |-----------+-------------+-----------+-----------+---------+-------------+---------------|
		// | ticket_id | ticket_type | requestor | req_start | req_end |
		// ticket_text | ticket_status |
		// |-----------+-------------+-----------+-----------+---------+-------------+---------------|

		query = "select    ticket_id, " + "ticket_type, " + "requestor, " + "req_start, " + "req_end, "
				+ "ticket_text, " + "ticket_status " + "from allrequests where ticket_id='" + ticket_id + "'";

		web_log.info("Fetching the ticket details for the Ticket ID - " + ticket_id);
		// OpsRequestsPOJO dor = new OpsRequestsPOJO();
		try {
			Statement statement = this.connection.createStatement();
			ResultSet rs = statement.executeQuery(query);
			while (rs.next()) {
				OpsRequestsPOJO dor = new OpsRequestsPOJO();
				dor.setTicket_id(rs.getString(1));
				dor.setTicket_type(rs.getString(2));
				dor.setRequestor(rs.getString(3));
				dor.setReq_start(rs.getTimestamp(4));
				dor.setReq_end(rs.getTimestamp(5));
				dor.setTicket_text(rs.getString(6));
				dor.setTicket_status(rs.getString(7));
				dorp.add(dor);
				dor = null;
			}
		} catch (Exception e) {
			web_log.error("For Ticket ID - " + ticket_id + " Unable to fetch the records - " + e);
		}
		this.closeDBConnection();
		return dorp;
	}
	
	public String getEmail(String id){
		String email="";
		
		ReadConfiguration rc = new ReadConfiguration();
		PropertyConfigurator.configure(rc.getLog4j());
		this.connection = getDBConnection();
		String query = "select email from users where shortid='"+id+"'";
		
		try {
			Statement statement = this.connection.createStatement();
			ResultSet rs = statement.executeQuery(query);
				while (rs.next()) {
					email=rs.getString(1);
				}
			}catch (Exception e) {
				e.printStackTrace();//web_log.error("For User ID - " + id + " Unable to fetch the email address - " + e);
			}
			this.closeDBConnection();
		return email;
	}

}

// select count(*), req_start::timestamp::date as req_date from allrequests
// where req_start::timestamp::date < current_date group by req_date order by 2
// asc limit 10 ;
// select count(*), ticket_type, req_start::timestamp::date as req_date from
// allrequests where req_start::timestamp::date < current_date group by
// ticket_type, req_date order by 3 asc limit 10 ;
// select count(*), ticket_type, req_start::timestamp::date as req_date from
// allrequests where req_start::timestamp::date < current_date and
// requestor='hemant' group by ticket_type, req_date order by 3 asc limit 10 ;
// create view last10days as select generate_series(CURRENT_DATE - 9 ,
// CURRENT_DATE, '1 day'::interval)::date;
// create view last10dayrequests as select req_start::timestamp::date as
// req_date, count(*) from allrequests where req_start::timestamp::date <
// current_date group by req_date order by 1 asc limit 10 ;
// select b.dates, COALESCE(a.count,0) as ticketscount from last10dayrequests a
// right join last10days b on a.req_date = b.dates;
