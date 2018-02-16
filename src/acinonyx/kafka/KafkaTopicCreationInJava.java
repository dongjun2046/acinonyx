package acinonyx.kafka;

import java.util.Properties;
import kafka.admin.AdminUtils;
import kafka.utils.ZKStringSerializer$;
import kafka.utils.ZkUtils;

import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.ZkConnection;

import acinonyx.conf.ReadConfiguration;
import kafka.admin.RackAwareMode;
import kafka.admin.AclCommand;

public class KafkaTopicCreationInJava {
	public static void main(String[] args) throws Exception {
		createTopic();
	}

	public static void createTopic() {
		ZkClient zkClient = null;
		ZkUtils zkUtils = null;
		boolean createdTopic = false;
		try {
			String zookeeperHosts = "sgscvaiu0304.inedc.corpintra.net:2181";
			int sessionTimeOutInMs = 15 * 1000;
			int connectionTimeOutInMs = 10 * 1000;
			ReadConfiguration rconf = new ReadConfiguration();
			System.setProperty("java.security.krb5.conf", rconf.getKrb5Location());
			System.setProperty("java.security.auth.login.config", rconf.getKafkaJAASPath());

			zkClient = new ZkClient(zookeeperHosts, sessionTimeOutInMs, connectionTimeOutInMs,
					ZKStringSerializer$.MODULE$);
			zkUtils = new ZkUtils(zkClient, new ZkConnection(zookeeperHosts), false);

			String topicName = "testTopic199";
			int noOfPartitions = 1;
			int noOfReplication = 1;
			Properties topicConfiguration = new Properties();
			try {
				AdminUtils.createTopic(zkUtils, topicName, noOfPartitions, noOfReplication, topicConfiguration,
						RackAwareMode.Enforced$.MODULE$);
				createdTopic = true;
			} catch (kafka.common.TopicExistsException tee) {
				createdTopic = true;
				System.out.println("Topic already exists..!");
			} catch (Exception e) {
				createdTopic = false;
				System.out.println("Error while creating the topic : " + e);
				System.out.println("No ACLs processed..!");
			}

			String[] cmdPArm = { "--add", "--allow-principal", "user:hemant2@INEDC.CORPINTRA.NET", "--operation", "ALL",
					"--topic", topicName, "--authorizer-properties",
					"zookeeper.connect=sgscvaiu0304.inedc.corpintra.net:2181" };
			// if(createdTopic)
			AclCommand.main(cmdPArm);

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			zkUtils.close();
			zkClient.close();
		}
	}
}
