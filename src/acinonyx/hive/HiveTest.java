package acinonyx.hive;

import java.sql.*;
import org.apache.hadoop.security.UserGroupInformation;

import acinonyx.conf.ReadConfiguration;

public class HiveTest {
	public static void main(String args[]) {
		try {
			ReadConfiguration rc = new ReadConfiguration();
			System.setProperty("java.security.krb5.conf", rc.getKrb5Location());

			org.apache.hadoop.conf.Configuration conf = new org.apache.hadoop.conf.Configuration();
			conf.set("hadoop.security.authentication", "Kerberos");
			UserGroupInformation.setConfiguration(conf);
			UserGroupInformation.loginUserFromKeytab(rc.getHiveAdminPrincipal(), rc.getHiveAdminKeytab());
			Class.forName("org.apache.hive.jdbc.HiveDriver");
			// Connection con =
			// DriverManager.getConnection("jdbc:hive2://192.168.131.129:2181/;serviceDiscoveryMode=zooKeeper;zooKeeperNamespace=hiveserver2");
			// Connection con =
			// DriverManager.getConnection("jdbc:hive2://"+rc.getHiveHost().trim()
			// + ":" + rc.getHivePort().trim() +
			// "/default;principal="+rc.getHiveAdminPrincipal());
			Connection con = DriverManager.getConnection("jdbc:hive2://" + rc.getZooKeeperQuoram().trim()
					+ "/default;serviceDiscoveryMode=zooKeeper;zooKeeperNamespace=hiveserver2");
			Statement stmt = con.createStatement();
			String sql = "show databases";
			/*
			 * stmt.executeQuery("CREATE TABLE IF NOT EXISTS "
			 * +" employee2 ( eid int, name string, "
			 * +" salary string, destignation string)"
			 * +" COMMENT 'Employee details'" +" ROW FORMAT DELIMITED"
			 * +" FIELDS TERMINATED BY '\t'" +" LINES TERMINATED BY '\n'"
			 * +" STORED AS TEXTFILE");
			 */
			stmt.executeQuery(sql);
			ResultSet res = stmt.executeQuery(sql);
			while (res.next()) {
				System.out.println(res.getString(1));
			}
			stmt.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}