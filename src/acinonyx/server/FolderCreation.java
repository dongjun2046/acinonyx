package acinonyx.server;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import acinonyx.conf.ReadConfiguration;

public class FolderCreation {

	public List<String> createFolders(String tid, String folderList, String user, String group, String hostName) {
		final Logger web_log = Logger.getLogger(FolderCreation.class);
		ReadConfiguration readConf = new ReadConfiguration();
		PropertyConfigurator.configure(readConf.getLog4j());

		String restrictConf = readConf.getHdfsRestrictionBeginswith().trim();
		List<String> restrictList = new ArrayList<String>();
		List<String> msg = new ArrayList<String>();
		List<String> validList = new ArrayList<String>();

		if (restrictConf.equals("") || restrictConf.isEmpty() || !(restrictConf.matches("[A-Za-z0-9_,]+"))) {
			msg.add("Restricted list provided in the configuration has issues. Using default - org");
			web_log.error(tid
					+ " Restricted list provided in the configuration has issues. Please check \"hdfs.restriction.beginswith\". Using default - org");
			restrictList.add("org");
		} else {
			String[] restrictFolders = restrictConf.split(",");
			for (String restrictDir : restrictFolders) {
				if (restrictDir.matches("[A-Za-z0-9_]+")) {
					restrictList.add(restrictDir.trim());
				}
			}
		}

		String[] folders = folderList.split("\\n");

		for (String folder : folders) {
			folder = folder.trim();
			if (folder.matches("[A-Za-z0-9_/]+") && folder.startsWith("/")) {
				String[] dirs = folder.split("/");
				if (restrictList.contains(dirs[1])) {
					validList.add(folder);
				} else {
					web_log.error(tid + " Invalid server path              : " + folder);
					msg.add("Invalid server path              : " + folder);
				}
			}
		}

		if (validList.size() == 0) {
			web_log.error(tid + " No folders to create");
			msg.add("No folders to create");
		} else {
			SSHExecute sse = new SSHExecute();
			String allPath = "";
			for (String path : validList) {
				allPath += (path + " ");
			}
			int retCode = 9;

			retCode = sse.executeCommand(tid, "mkdir -p " + allPath, hostName);
			if (retCode == 0)
				msg.add("Created folder(s) " + allPath + " succesfully");
			else if (retCode == -1)
				msg.add("");
			else
				msg.add("Issue during folder creation");

			retCode = sse.executeCommand(tid, "chmod 750 " + allPath, hostName);
			if (retCode == 0)
				msg.add("Set default permissions on folder succesfully");
			else if (retCode == -1)
				msg.add("");
			else
				msg.add("Issue during setting permissions");

			retCode = sse.executeCommand(tid, "chown " + user + ":" + group + " " + allPath, hostName);
			if (retCode == 0)
				msg.add("Set default ownership on folder succesfully");
			else if (retCode == -1)
				msg.add("");
			else
				msg.add("Issue during setting ownership");
		}
		return msg;
	}

}
