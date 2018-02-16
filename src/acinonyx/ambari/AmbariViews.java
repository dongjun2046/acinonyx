package acinonyx.ambari;

import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import acinonyx.conf.ReadConfiguration;

public class AmbariViews {

	boolean execute(String tid, String uname, List<String> viewList) {
		boolean status = true;
		final Logger web_log = Logger.getLogger(AmbariViews.class);
		web_log.info("Reading Ambari Views Configurations...!");
		ReadConfiguration readConf = new ReadConfiguration();
		PropertyConfigurator.configure(readConf.getLog4j());

		try {

			URL url = new URL("http://" + readConf.getAmbariViewsHost().trim() + ":"
					+ readConf.getAmbariViewsPort().trim() + "/api/v1/ldap_sync_events");
			String authStr = readConf.getAmbariViewsAdminUsr().trim() + ":" + readConf.getAmbariViewsAdminPwd().trim();

			String authEncoded = Base64.encodeBase64String(authStr.getBytes());

			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("POST");
			connection.setUseCaches(false);
			connection.setDoInput(true);
			connection.setDoOutput(true);
			connection.setRequestProperty("Authorization", "Basic " + authEncoded);
			connection.setRequestProperty("X-Requested-By", "ambari");

			web_log.info(tid + " Checking with AmbariViews");
			String syncJsonData = "[{\"Event\": {\"specs\": [{\"principal_type\": \"users\", \"sync_type\": \"specific\", \"names\":\""
					+ uname + "\"}]}}]";
			DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
			wr.writeBytes(syncJsonData);
			wr.flush();
			wr.close();
			int rcode = connection.getResponseCode();
			TimeUnit.SECONDS.sleep(10);
			if (rcode == 201) {
				web_log.info(tid + " Sync process for user : " + uname + " Completed..!");
				String viewJsonData = "[{\"PrivilegeInfo\":{\"permission_name\":\"VIEW.USER\",\"principal_name\":\""
						+ uname + "\",\"principal_type\":\"USER\"}}]";
				String vurl = "http://" + readConf.getAmbariViewsHost().trim() + ":"
						+ readConf.getAmbariViewsPort().trim() + "/api/v1/views/"
						+ readConf.getAmbariViewsFileViewName() + "/versions/" + readConf.getAmbariViewsFileVersion()
						+ "/instances/" + readConf.getAmbariViewsFileInstance() + "/privileges";
				String authStr2 = readConf.getAmbariViewsAdminUsr().trim() + ":"
						+ readConf.getAmbariViewsAdminPwd().trim();

				for (String viewName : viewList) {
					vurl = "http://" + readConf.getAmbariViewsHost().trim() + ":" + readConf.getAmbariViewsPort().trim()
							+ "/api/v1/views/" + readConf.getAmbariViewsFileViewName() + "/versions/"
							+ readConf.getAmbariViewsFileVersion() + "/instances/"
							+ readConf.getAmbariViewsFileInstance() + "/privileges";
					switch (viewName) {
					case "file":
						vurl = "http://" + readConf.getAmbariViewsHost().trim() + ":"
								+ readConf.getAmbariViewsPort().trim() + "/api/v1/views/"
								+ readConf.getAmbariViewsFileViewName() + "/versions/"
								+ readConf.getAmbariViewsFileVersion() + "/instances/"
								+ readConf.getAmbariViewsFileInstance() + "/privileges";
						break;
					case "hive1":
						vurl = "http://" + readConf.getAmbariViewsHost().trim() + ":"
								+ readConf.getAmbariViewsPort().trim() + "/api/v1/views/"
								+ readConf.getAmbariViewsHive1ViewName() + "/versions/"
								+ readConf.getAmbariViewsHive1Version() + "/instances/"
								+ readConf.getAmbariViewsHive1Instance() + "/privileges";
						break;
					case "hive2":
						vurl = "http://" + readConf.getAmbariViewsHost().trim() + ":"
								+ readConf.getAmbariViewsPort().trim() + "/api/v1/views/"
								+ readConf.getAmbariViewsHive2ViewName() + "/versions/"
								+ readConf.getAmbariViewsHive2Version() + "/instances/"
								+ readConf.getAmbariViewsHive2Instance() + "/privileges";
						break;
					case "tez":
						vurl = "http://" + readConf.getAmbariViewsHost().trim() + ":"
								+ readConf.getAmbariViewsPort().trim() + "/api/v1/views/"
								+ readConf.getAmbariViewsTezViewName() + "/versions/"
								+ readConf.getAmbariViewsTezVersion() + "/instances/"
								+ readConf.getAmbariViewsTezInstance() + "/privileges";
						break;
					case "pig":
						vurl = "http://" + readConf.getAmbariViewsHost().trim() + ":"
								+ readConf.getAmbariViewsPort().trim() + "/api/v1/views/"
								+ readConf.getAmbariViewsPigViewName() + "/versions/"
								+ readConf.getAmbariViewsPigVersion() + "/instances/"
								+ readConf.getAmbariViewsPigInstance() + "/privileges";
						break;
					case "oozie":
						vurl = "http://" + readConf.getAmbariViewsHost().trim() + ":"
								+ readConf.getAmbariViewsPort().trim() + "/api/v1/views/"
								+ readConf.getAmbariViewsOozieeViewName() + "/versions/"
								+ readConf.getAmbariViewsOozieeVersion() + "/instances/"
								+ readConf.getAmbariViewsOozieeInstance() + "/privileges";
						break;
					case "storm":
						vurl = "http://" + readConf.getAmbariViewsHost().trim() + ":"
								+ readConf.getAmbariViewsPort().trim() + "/api/v1/views/"
								+ readConf.getAmbariViewsStormViewName() + "/versions/"
								+ readConf.getAmbariViewsStormVersion() + "/instances/"
								+ readConf.getAmbariViewsStormInstance() + "/privileges";
						break;
					case "yarn":
						vurl = "http://" + readConf.getAmbariViewsHost().trim() + ":"
								+ readConf.getAmbariViewsPort().trim() + "/api/v1/views/"
								+ readConf.getAmbariViewsYARNViewName() + "/versions/"
								+ readConf.getAmbariViewsYARNVersion() + "/instances/"
								+ readConf.getAmbariViewsYARNInstance() + "/privileges";
						break;

					}

					URL viewURL = new URL(vurl);

					String authEncoded2 = Base64.encodeBase64String(authStr2.getBytes());
					HttpURLConnection viewConn = (HttpURLConnection) viewURL.openConnection();
					viewConn.setRequestMethod("POST");
					viewConn.setUseCaches(false);
					viewConn.setDoInput(true);
					viewConn.setDoOutput(true);
					viewConn.setRequestProperty("Authorization", "Basic " + authEncoded2);
					viewConn.setRequestProperty("X-Requested-By", "ambari");

					DataOutputStream wr2 = new DataOutputStream(viewConn.getOutputStream());
					wr2.writeBytes(viewJsonData);
					wr2.flush();
					wr2.close();
					int rcode2 = viewConn.getResponseCode();
					if (rcode2 == 201) {
						web_log.info(
								tid + " Provided VIEW.USER privilege to user : " + uname + " for view : " + viewName);
					} else if (rcode2 == 409) {
						web_log.info(tid + " VIEW.USER privilege already exists for user : " + uname + " on view : "
								+ viewName);
					} else {
						web_log.error(tid + " Unable to assign the privilege - " + uname + " for view : " + viewName);
						status = false;
					}
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