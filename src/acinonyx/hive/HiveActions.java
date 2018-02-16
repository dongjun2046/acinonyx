package acinonyx.hive;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import org.apache.hadoop.security.UserGroupInformation;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import acinonyx.conf.ReadConfiguration;

import java.util.ArrayList;
import java.util.List;

public class HiveActions {
	public List<String> getDatabases() {
		List<String> databaseList = new ArrayList<String>();
		ReadConfiguration readConf = new ReadConfiguration();
		final Logger web_log = Logger.getLogger(HiveActions.class);
		PropertyConfigurator.configure(readConf.getLog4j());
		try {
			System.setProperty("java.security.krb5.conf", readConf.getKrb5Location());
			org.apache.hadoop.conf.Configuration conf = new org.apache.hadoop.conf.Configuration();
			conf.set("hadoop.security.authentication", "Kerberos");
			UserGroupInformation.setConfiguration(conf);
			UserGroupInformation.loginUserFromKeytab(readConf.getHiveAdminPrincipal(), readConf.getHiveAdminKeytab());
			Class.forName("org.apache.hive.jdbc.HiveDriver");
			Connection con = DriverManager.getConnection("jdbc:hive2://" + readConf.getZooKeeperQuoram().trim()
					+ "/default;serviceDiscoveryMode=zooKeeper;zooKeeperNamespace=hiveserver2");
			Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			String sql = "show databases";
			web_log.info("Connected to Hive Database");

			ResultSet res = stmt.executeQuery(sql);
			web_log.info("Fetching the database details");
			databaseList = new ArrayList<String>();
			while (res.next()) {
				String dbname = res.getString(1);
				if (!dbname.equals("default"))
					databaseList.add(dbname);
			}
			stmt.close();
			con.close();
		} catch (Exception e) {
			web_log.error("Issue while fetching database list - " + e);
			// e.printStackTrace();
			databaseList.add("default");
		}
		return databaseList;
	}

	public List<String> executeQuery(String database, String query) {

		ReadConfiguration readConf = new ReadConfiguration();
		final Logger web_log = Logger.getLogger(HiveActions.class);
		PropertyConfigurator.configure(readConf.getLog4j());

		List<String> messages = new ArrayList<String>();

		try {
			System.setProperty("java.security.krb5.conf", readConf.getKrb5Location());
			org.apache.hadoop.conf.Configuration conf = new org.apache.hadoop.conf.Configuration();
			UserGroupInformation.setConfiguration(conf);
			UserGroupInformation.loginUserFromKeytab(readConf.getHiveAdminPrincipal(), readConf.getHiveAdminKeytab());
			Class.forName("org.apache.hive.jdbc.HiveDriver");

			Connection con = DriverManager.getConnection("jdbc:hive2://" + readConf.getZooKeeperQuoram() + "/"
					+ database + ";serviceDiscoveryMode=zooKeeper;zooKeeperNamespace=hiveserver2");
			// Connection con =
			// DriverManager.getConnection(readConf.getHiveConnectionString());

			Statement stmt = con.createStatement(); // con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			web_log.info("Executing the query " + query);
			// stmt.execute(query.getBytes("UTF-8").toString());
			String conQuery = new String(query.getBytes(), "UTF-8");
			stmt.execute(conQuery);
			web_log.info("Completed. Closing the connection");
			stmt.close();
			con.close();
			web_log.info("Hive Connection closed");
			messages.add("Created the table as per the query : " + query);
		} catch (Exception e) {
			messages.add("Error during executing the query");
			messages.add(e.toString());
			web_log.error(e);
		}
		return messages;
	}

}
