package acinonyx.kafka;

import java.util.Properties;
import kafka.admin.AdminUtils;
import kafka.utils.ZKStringSerializer$;
import kafka.utils.ZkUtils;
import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.ZkConnection;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import acinonyx.conf.ReadConfiguration;
import kafka.admin.RackAwareMode;
import kafka.admin.AclCommand;

import java.util.ArrayList;
import java.util.List;

public class KafkaTopicAclCreation {
	boolean createTopicWithACL(String tid, String topicName, int partition, int repl, String princ,
			List<String> permsList) {
		ReadConfiguration rconf = new ReadConfiguration();
		final Logger web_log = Logger.getLogger(KafkaTopicAclCreation.class);
		PropertyConfigurator.configure(rconf.getLog4j());

		ZkClient zkClient = null;
		ZkUtils zkUtils = null;
		boolean createdTopic = false;

		String zookeeperHosts = rconf.getKafkaQuoram();
		int sessionTimeOutInMs = 15 * 1000;
		int connectionTimeOutInMs = 10 * 1000;

		web_log.info(tid + " KafkaSessionTimeout : " + sessionTimeOutInMs + " & ConnectionTimeout : "
				+ connectionTimeOutInMs);
		System.setProperty("java.security.krb5.conf", rconf.getKrb5Location());
		System.setProperty("java.security.auth.login.config", rconf.getKafkaJAASPath());

		Properties topicConfiguration = new Properties();

		try {
			zkClient = new ZkClient(zookeeperHosts, sessionTimeOutInMs, connectionTimeOutInMs,
					ZKStringSerializer$.MODULE$);
			zkUtils = new ZkUtils(zkClient, new ZkConnection(zookeeperHosts), false);
			AdminUtils.createTopic(zkUtils, topicName, partition, repl, topicConfiguration,
					RackAwareMode.Enforced$.MODULE$);
			System.out.println("yes 3");
			web_log.info(tid + " Topic " + topicName + " created. Proceeding with ACL.");
			createdTopic = true;
		} catch (kafka.common.TopicExistsException tee) {
			createdTopic = true;
			web_log.info(tid + " Topic " + topicName + " already exists. Proceeding with ACL.");
		} catch (Exception e) {
			createdTopic = false;
			web_log.error(tid + " Error while creating the topic : " + e);
			web_log.error(tid + " No ACLs processed..!");
		}

		List<String> parmList = new ArrayList<String>();
		parmList.add("--add");
		parmList.add("--allow-principal");
		parmList.add("user:" + princ);
		parmList.add("--topic");
		parmList.add(topicName);

		for (String perm : permsList) {
			parmList.add("--operation");
			parmList.add(perm);
		}

		parmList.add("--authorizer-properties");
		parmList.add("zookeeper.connect=" + zookeeperHosts);

		String[] cmdParm = parmList.toArray(new String[parmList.size()]);

		if (createdTopic) {
			AclCommand.main(cmdParm);
			web_log.info(tid + " Set the ACL for the topic : " + topicName);
		}

		try {
			zkUtils.close();
			zkClient.close();
		} catch (Exception e) {
			web_log.error(tid + " Error while closing the ZK Connection : " + e);
		}
		return createdTopic;
	}
}
