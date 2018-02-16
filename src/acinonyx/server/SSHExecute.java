package acinonyx.server;

import java.io.InputStream;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

import acinonyx.conf.ReadConfiguration;

public class SSHExecute {

	public int executeCommand(String tid, String command, String hostName) {

		ReadConfiguration readConf = new ReadConfiguration();
		String host = hostName;
		String user = readConf.getUagUser();
		String privateKey = readConf.getKeyPath();
		Channel channel = null;
		Session session = null;
		final Logger web_log = Logger.getLogger(SSHExecute.class);
		PropertyConfigurator.configure(readConf.getLog4j());
		web_log.info(tid + " Executing the command : " + command);
		try {
			JSch jsch = new JSch();
			jsch.addIdentity(privateKey);
			session = jsch.getSession(user, host, 22);
			// session.setConfig("PreferredAuthentications",
			// "publickey,keyboard-interactive,password");
			session.setConfig("PreferredAuthentications", "publickey,hostbased");
			java.util.Properties config = new java.util.Properties();
			config.put("StrictHostKeyChecking", "no");
			// session.setPassword(password);
			session.setConfig(config);
			session.connect();
			channel = session.openChannel("exec");
			((ChannelExec) channel).setCommand(command);
			channel.setInputStream(null);
			InputStream in = channel.getInputStream();
			channel.connect(30000);
			// channel.connect();
			channel.run();
			byte[] tmp = new byte[1024];
			while (true) {
				while (in.available() > 0) {
					int i = in.read(tmp, 0, 1024);
					if (i < 0)
						break;
				}
				if (channel.isClosed()) {
					break;
				}
				try {
					Thread.sleep(1000);
				} catch (Exception ee) {
					web_log.error(tid + " Sleep Error " + ee);
				}
			}
		} catch (Exception e) {
			web_log.error(tid + " Error while executing " + command + " on server : " + hostName + "\n" + e);
		}
		try {
			channel.disconnect();
			session.disconnect();
		} catch (Exception e) {
			web_log.error(tid + " Error while closing the connection on server : " + hostName + "\n" + e);
		}
		return channel.getExitStatus();
	}
}
