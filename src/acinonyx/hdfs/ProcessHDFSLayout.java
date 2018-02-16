package acinonyx.hdfs;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import acinonyx.conf.ReadConfiguration;

import org.apache.hadoop.security.UserGroupInformation;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

public class ProcessHDFSLayout {

	public List<String> processHDFSLayout(String tid, String foldersList) {

		final Logger web_log = Logger.getLogger(ProcessHDFSLayout.class);
		ReadConfiguration readConf = new ReadConfiguration();
		PropertyConfigurator.configure(readConf.getLog4j());

		String restrictConf = readConf.getHdfsRestrictionBeginswith().trim();
		List<String> restrictList = new ArrayList<String>();
		List<String> msg = new ArrayList<String>();
		List<String> validList = new ArrayList<String>();

		if (restrictConf.equals("") || restrictConf.isEmpty() || !(restrictConf.matches("[A-Za-z0-9_,]+"))) {
			msg.add("Restricted list provided in the configuration has issues. Using default - org");
			web_log.error(tid
					+ " - Restricted list provided in the configuration has issues. Please check \"hdfs.restriction.beginswith\". Using default - org");
			restrictList.add("org");
		} else {
			String[] restrictFolders = restrictConf.split(",");
			for (String restrictDir : restrictFolders) {
				if (restrictDir.matches("[A-Za-z0-9_]+")) {
					restrictList.add(restrictDir.trim());
				}
			}
		}

		String[] folders = foldersList.split("\\n");

		for (String folder : folders) {
			folder = folder.trim();
			if (folder.matches("[A-Za-z0-9_/]+") && folder.startsWith("/")) {
				String[] dirs = folder.split("/");
				if (restrictList.contains(dirs[1])) {
					validList.add(folder);
				} else {
					web_log.error(tid + " - Invalid path : " + folder);
					msg.add("Invalid path              : " + folder);
				}
			}
		}

		System.setProperty("java.security.krb5.conf", readConf.getKrb5Location());
		try {
			Configuration conf = new Configuration();
			UserGroupInformation.setConfiguration(conf);
			UserGroupInformation.loginUserFromKeytab(readConf.getHdfsAdminPrinciple(), readConf.getHdfsAdminKeytab());
			FileSystem fileSystem = FileSystem.get(conf);
			for (String path : validList) {
				Path newFolderPath = new Path(path);
				if (!fileSystem.exists(newFolderPath)) {
					if (fileSystem.mkdirs(newFolderPath)) {
						web_log.info(tid + " - Created the path : " + path);
						msg.add("Created the path          : " + path);
					} else {
						web_log.info(tid + " - Failed to create the path : " + path);
						msg.add("Failed to create the path : " + path);
					}
				} else {
					web_log.info(tid + " - Path already exists : " + path);
					msg.add("Path already exists : " + path);
				}
			}
			fileSystem.close();
		} catch (Exception ex) {
			web_log.error(ex);
			msg.add(ex.toString());
		}
		return msg;
	}

}
