package acinonyx.common;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;

import java.sql.ResultSet;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import acinonyx.conf.ReadConfiguration;

public class CheckUser {

	int checkIfUserExists(String userName, String password) {
		int retVal = -9;

		ReadConfiguration rc = new ReadConfiguration();
		final Logger web_log = Logger.getLogger(CheckUser.class);
		PropertyConfigurator.configure(rc.getLog4j());

		String dbhost = rc.getDbhost();
		String dbport = rc.getDbport();
		String dbclass = rc.getDbdriverclass();
		String dbname = rc.getDbname();
		String dbuser = rc.getDbuser();
		String dbpwd = rc.getDbpassword();
		String dburl = "jdbc:postgresql://" + dbhost + ":" + dbport + "/" + dbname;

		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		String query = "select shortid, type, password from users where shortid = '" + userName + "'";

		try {
			Class.forName(dbclass);
		} catch (ClassNotFoundException e) {
			web_log.error("JDBC Driver Class Not Found " + e);
			retVal = -1;
		}

		try {
			conn = DriverManager.getConnection(dburl, dbuser, dbpwd);
		} catch (Exception e) {
			web_log.error("Connection Error " + e);
			retVal = -1;
		}

		if (retVal == -9) {
			try {
				stmt = conn.createStatement();
				rs = stmt.executeQuery(query);
				if (rs.next()) {
					retVal = rs.getInt(2);// 0 is admin, 1 is requester, 2 is
											// approver
					if (password.equals(rs.getString(3))) {
						web_log.info("User authenticated and is of type : " + retVal);
					} else {
						retVal = 999;
						web_log.info("User : " + userName + " authentication failed");
					}
				} else {
					web_log.error("User doesn't have access");
				}
				conn.close();
			} catch (Exception ex) {
				web_log.error("Error " + ex);
				retVal = -2;
			}
		}
		return retVal;
	}

}
