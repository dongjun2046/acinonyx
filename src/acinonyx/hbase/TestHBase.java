package acinonyx.hbase;

import java.io.IOException;

import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.MasterNotRunningException;
import org.apache.hadoop.hbase.client.HBaseAdmin;
import org.apache.hadoop.security.UserGroupInformation;

import acinonyx.conf.ReadConfiguration;

import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.ZooKeeperConnectionException;
import org.apache.hadoop.conf.Configuration;

public class TestHBase {

	public static void main(String[] args) {
		ReadConfiguration readConf = new ReadConfiguration();
		System.setProperty("java.security.krb5.conf", readConf.getKrb5Location());
		// Instantiating configuration class
		Configuration con = HBaseConfiguration.create();
		UserGroupInformation.setConfiguration(con);
		try {
			UserGroupInformation.loginUserFromKeytab("hbase-kaizen@INEDC.CORPINTRA.NET",
					"C:/Users/hedindi/git/wings/conf/hbase.headless.keytab");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		con.set("hbase.zookeeper.quorum", "sgscvaiu0304.inedc.corpintra.net");
		con.set("hbase.security.authentication", "kerberos");
		con.set("hbase.zookeeper.property.clientPort", "2181");
		con.set("zookeeper.znode.parent", "/hbase-secure");
		con.set("hbase.rpc.protection", "authentication");
		con.set("hadoop.security.authentication", "kerberos");
		System.setProperty("java.security.auth.login.config", "C:/Users/hedindi/git/wings/conf/kafka_client_jaas.conf");
		// Instantiating HbaseAdmin class
		HBaseAdmin admin = null;
		try {
			admin = new HBaseAdmin(con);
		} catch (MasterNotRunningException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ZooKeeperConnectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Instantiating table descriptor class
		HTableDescriptor tableDescriptor = new HTableDescriptor(TableName.valueOf("empdb"));

		// Adding column families to table descriptor
		tableDescriptor.addFamily(new HColumnDescriptor("personal"));
		tableDescriptor.addFamily(new HColumnDescriptor("professional"));

		// Execute the table through admin
		try {
			admin.createTable(tableDescriptor);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(" Table created ");
	}
}