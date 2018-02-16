package acinonyx.ambari;

import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.TimeUnit;
import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import acinonyx.conf.ReadConfiguration;

public class AmbariReadOnly {

	boolean execute(String tid, String uname) {
		boolean status = true;
		final Logger web_log = Logger.getLogger(AmbariReadOnly.class);
		web_log.info(tid + " Reading Ambari Configurations...!");
		ReadConfiguration readConf = new ReadConfiguration();
		PropertyConfigurator.configure(readConf.getLog4j());

		try {

			URL url = new URL("http://" + readConf.getAmbariHost().trim() + ":" + readConf.getAmbariPort().trim()
					+ "/api/v1/ldap_sync_events");
			String authStr = readConf.getAmbariAdminUsr().trim() + ":" + readConf.getAmbariAdminPwd().trim();

			String authEncoded = Base64.encodeBase64String(authStr.getBytes());

			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("POST");
			connection.setUseCaches(false);
			connection.setDoInput(true);
			connection.setDoOutput(true);
			connection.setRequestProperty("Authorization", "Basic " + authEncoded);
			connection.setRequestProperty("X-Requested-By", "ambari");

			web_log.info(tid + " Checking with Ambari");
			String postJsonData = "[{\"Event\": {\"specs\": [{\"principal_type\": \"users\", \"sync_type\": \"specific\", \"names\":\""
					+ uname + "\"}]}}]";

			DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
			wr.writeBytes(postJsonData);
			wr.flush();
			wr.close();
			int rcode = connection.getResponseCode();
			TimeUnit.SECONDS.sleep(10);
			if (rcode == 201) {
				web_log.info(tid + " Sync process for user : " + uname + " Completed..!");
				String provideAccess = "[{\"PrivilegeInfo\":{\"permission_name\":\"CLUSTER.USER\",\"principal_name\":\""
						+ uname + "\",\"principal_type\":\"USER\"}}]";

				URL url2 = new URL("http://" + readConf.getAmbariHost().trim() + ":" + readConf.getAmbariPort().trim()
						+ "/api/v1/clusters/" + readConf.getAmbariClusterName() + "/privileges");
				String authStr2 = readConf.getAmbariAdminUsr().trim() + ":" + readConf.getAmbariAdminPwd().trim();

				String authEncoded2 = Base64.encodeBase64String(authStr2.getBytes());

				HttpURLConnection connection2 = (HttpURLConnection) url2.openConnection();
				connection2.setRequestMethod("POST");
				connection2.setUseCaches(false);
				connection2.setDoInput(true);
				connection2.setDoOutput(true);
				connection2.setRequestProperty("Authorization", "Basic " + authEncoded2);
				connection2.setRequestProperty("X-Requested-By", "ambari");

				DataOutputStream wr2 = new DataOutputStream(connection2.getOutputStream());
				wr2.writeBytes(provideAccess);
				wr2.flush();
				wr2.close();
				int rcode2 = connection2.getResponseCode();
				if (rcode2 == 201) {
					web_log.info(tid + " Provided CLUSTER.USER privilege to user : " + uname);
				} else {
					web_log.error(tid + " Unable to assign the privilege - " + uname);
					status = false;
				}
			} else {
				web_log.error(tid + " Unable to Sync User - " + uname);
				status = false;
			}

		} catch (Exception ex) {
			web_log.error(ex);
			status = false;
		}

		return status;
	}
}