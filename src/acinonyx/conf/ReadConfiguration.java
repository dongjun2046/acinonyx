package acinonyx.conf;

import java.io.FileInputStream;
import java.util.Properties;

public class ReadConfiguration {

	String workDir;
	String krb5Location;

	String rangerHost;
	String rangerPort;
	String rangerAdmin;
	String rangerAdminPassword;

	String ambariHost;
	String ambariPort;
	String ambariAdminUsr;
	String ambariAdminPwd;
	String ambariClusterName;

	String ambariViewsHost;
	String ambariViewsPort;
	String ambariViewsAdminUsr;
	String ambariViewsAdminPwd;

	String ambariViewsFileViewName;
	String ambariViewsFileVersion;
	String ambariViewsFileInstance;

	String ambariViewsHive1ViewName;
	String ambariViewsHive1Version;
	String ambariViewsHive1Instance;

	String ambariViewsHive2ViewName;
	String ambariViewsHive2Version;
	String ambariViewsHive2Instance;

	String ambariViewsTezViewName;
	String ambariViewsTezVersion;
	String ambariViewsTezInstance;

	String ambariViewsPigViewName;
	String ambariViewsPigVersion;
	String ambariViewsPigInstance;

	String ambariViewsOozieeViewName;
	String ambariViewsOozieeVersion;
	String ambariViewsOozieeInstance;

	String ambariViewsStormViewName;
	String ambariViewsStormVersion;
	String ambariViewsStormInstance;

	String ambariViewsYARNViewName;
	String ambariViewsYARNVersion;
	String ambariViewsYARNInstance;

	String hiveHost;
	String hivePort;
	String hiveAdminPrincipal;
	String hiveAdminKeytab;
	String hiveConnectionString;
	String zooKeeperQuoram;

	String hdfsAdminPrinciple;
	String hdfsAdminKeytab;
	String hdfsRestrictionBeginswith;

	String kafkaQuoram;
	String kafkaJAASPath;

	String uagHostname;
	String keyPath;
	String uagUser;
	String edgeRestrictionBeginswith;

	String log4j;

	String dbhost;
	String dbport;
	String dbdriverclass;
	String dbname;
	String dbuser;
	String dbpassword;
	
	String smtphost;
	String smtpport;
	String smtpsender;

	public String getSmtphost() {
		return smtphost;
	}

	public void setSmtphost(String smtphost) {
		this.smtphost = smtphost;
	}

	public String getSmtpport() {
		return smtpport;
	}

	public void setSmtpport(String smtpport) {
		this.smtpport = smtpport;
	}

	public String getSmtpsender() {
		return smtpsender;
	}

	public void setSmtpsender(String smtpsender) {
		this.smtpsender = smtpsender;
	}

	public String getWorkDir() {
		return workDir;
	}

	public void setWorkDir(String workDir) {
		this.workDir = workDir;
	}

	public String getKrb5Location() {
		return krb5Location;
	}

	public void setKrb5Location(String krb5Location) {
		this.krb5Location = krb5Location;
	}

	public String getRangerHost() {
		return rangerHost;
	}

	public void setRangerHost(String rangerHost) {
		this.rangerHost = rangerHost;
	}

	public String getRangerPort() {
		return rangerPort;
	}

	public void setRangerPort(String rangerPort) {
		this.rangerPort = rangerPort;
	}

	public String getRangerAdmin() {
		return rangerAdmin;
	}

	public void setRangerAdmin(String rangerAdmin) {
		this.rangerAdmin = rangerAdmin;
	}

	public String getRangerAdminPassword() {
		return rangerAdminPassword;
	}

	public void setRangerAdminPassword(String rangerAdminPassword) {
		this.rangerAdminPassword = rangerAdminPassword;
	}

	public String getAmbariHost() {
		return ambariHost;
	}

	public void setAmbariHost(String ambariHost) {
		this.ambariHost = ambariHost;
	}

	public String getAmbariPort() {
		return ambariPort;
	}

	public void setAmbariPort(String ambariPort) {
		this.ambariPort = ambariPort;
	}

	public String getAmbariAdminUsr() {
		return ambariAdminUsr;
	}

	public void setAmbariAdminUsr(String ambariAdminUsr) {
		this.ambariAdminUsr = ambariAdminUsr;
	}

	public String getAmbariAdminPwd() {
		return ambariAdminPwd;
	}

	public void setAmbariAdminPwd(String ambariAdminPwd) {
		this.ambariAdminPwd = ambariAdminPwd;
	}

	public String getAmbariClusterName() {
		return ambariClusterName;
	}

	public void setAmbariClusterName(String ambariClusterName) {
		this.ambariClusterName = ambariClusterName;
	}

	public String getAmbariViewsHost() {
		return ambariViewsHost;
	}

	public void setAmbariViewsHost(String ambariViewsHost) {
		this.ambariViewsHost = ambariViewsHost;
	}

	public String getAmbariViewsPort() {
		return ambariViewsPort;
	}

	public void setAmbariViewsPort(String ambariViewsPort) {
		this.ambariViewsPort = ambariViewsPort;
	}

	public String getAmbariViewsAdminUsr() {
		return ambariViewsAdminUsr;
	}

	public void setAmbariViewsAdminUsr(String ambariViewsAdminUsr) {
		this.ambariViewsAdminUsr = ambariViewsAdminUsr;
	}

	public String getAmbariViewsAdminPwd() {
		return ambariViewsAdminPwd;
	}

	public void setAmbariViewsAdminPwd(String ambariViewsAdminPwd) {
		this.ambariViewsAdminPwd = ambariViewsAdminPwd;
	}

	public String getAmbariViewsFileViewName() {
		return ambariViewsFileViewName;
	}

	public void setAmbariViewsFileViewName(String ambariViewsFileViewName) {
		this.ambariViewsFileViewName = ambariViewsFileViewName;
	}

	public String getAmbariViewsFileVersion() {
		return ambariViewsFileVersion;
	}

	public void setAmbariViewsFileVersion(String ambariViewsFileVersion) {
		this.ambariViewsFileVersion = ambariViewsFileVersion;
	}

	public String getAmbariViewsFileInstance() {
		return ambariViewsFileInstance;
	}

	public void setAmbariViewsFileInstance(String ambariViewsFileInstance) {
		this.ambariViewsFileInstance = ambariViewsFileInstance;
	}

	public String getAmbariViewsHive1ViewName() {
		return ambariViewsHive1ViewName;
	}

	public void setAmbariViewsHive1ViewName(String ambariViewsHive1ViewName) {
		this.ambariViewsHive1ViewName = ambariViewsHive1ViewName;
	}

	public String getAmbariViewsHive1Version() {
		return ambariViewsHive1Version;
	}

	public void setAmbariViewsHive1Version(String ambariViewsHive1Version) {
		this.ambariViewsHive1Version = ambariViewsHive1Version;
	}

	public String getAmbariViewsHive1Instance() {
		return ambariViewsHive1Instance;
	}

	public void setAmbariViewsHive1Instance(String ambariViewsHive1Instance) {
		this.ambariViewsHive1Instance = ambariViewsHive1Instance;
	}

	public String getAmbariViewsHive2ViewName() {
		return ambariViewsHive2ViewName;
	}

	public void setAmbariViewsHive2ViewName(String ambariViewsHive2ViewName) {
		this.ambariViewsHive2ViewName = ambariViewsHive2ViewName;
	}

	public String getAmbariViewsHive2Version() {
		return ambariViewsHive2Version;
	}

	public void setAmbariViewsHive2Version(String ambariViewsHive2Version) {
		this.ambariViewsHive2Version = ambariViewsHive2Version;
	}

	public String getAmbariViewsHive2Instance() {
		return ambariViewsHive2Instance;
	}

	public void setAmbariViewsHive2Instance(String ambariViewsHive2Instance) {
		this.ambariViewsHive2Instance = ambariViewsHive2Instance;
	}

	public String getAmbariViewsTezViewName() {
		return ambariViewsTezViewName;
	}

	public void setAmbariViewsTezViewName(String ambariViewsTezViewName) {
		this.ambariViewsTezViewName = ambariViewsTezViewName;
	}

	public String getAmbariViewsTezVersion() {
		return ambariViewsTezVersion;
	}

	public void setAmbariViewsTezVersion(String ambariViewsTezVersion) {
		this.ambariViewsTezVersion = ambariViewsTezVersion;
	}

	public String getAmbariViewsTezInstance() {
		return ambariViewsTezInstance;
	}

	public void setAmbariViewsTezInstance(String ambariViewsTezInstance) {
		this.ambariViewsTezInstance = ambariViewsTezInstance;
	}

	public String getAmbariViewsPigViewName() {
		return ambariViewsPigViewName;
	}

	public void setAmbariViewsPigViewName(String ambariViewsPigViewName) {
		this.ambariViewsPigViewName = ambariViewsPigViewName;
	}

	public String getAmbariViewsPigVersion() {
		return ambariViewsPigVersion;
	}

	public void setAmbariViewsPigVersion(String ambariViewsPigVersion) {
		this.ambariViewsPigVersion = ambariViewsPigVersion;
	}

	public String getAmbariViewsPigInstance() {
		return ambariViewsPigInstance;
	}

	public void setAmbariViewsPigInstance(String ambariViewsPigInstance) {
		this.ambariViewsPigInstance = ambariViewsPigInstance;
	}

	public String getAmbariViewsOozieeViewName() {
		return ambariViewsOozieeViewName;
	}

	public void setAmbariViewsOozieeViewName(String ambariViewsOozieeViewName) {
		this.ambariViewsOozieeViewName = ambariViewsOozieeViewName;
	}

	public String getAmbariViewsOozieeVersion() {
		return ambariViewsOozieeVersion;
	}

	public void setAmbariViewsOozieeVersion(String ambariViewsOozieeVersion) {
		this.ambariViewsOozieeVersion = ambariViewsOozieeVersion;
	}

	public String getAmbariViewsOozieeInstance() {
		return ambariViewsOozieeInstance;
	}

	public void setAmbariViewsOozieeInstance(String ambariViewsOozieeInstance) {
		this.ambariViewsOozieeInstance = ambariViewsOozieeInstance;
	}

	public String getAmbariViewsStormViewName() {
		return ambariViewsStormViewName;
	}

	public void setAmbariViewsStormViewName(String ambariViewsStormViewName) {
		this.ambariViewsStormViewName = ambariViewsStormViewName;
	}

	public String getAmbariViewsStormVersion() {
		return ambariViewsStormVersion;
	}

	public void setAmbariViewsStormVersion(String ambariViewsStormVersion) {
		this.ambariViewsStormVersion = ambariViewsStormVersion;
	}

	public String getAmbariViewsStormInstance() {
		return ambariViewsStormInstance;
	}

	public void setAmbariViewsStormInstance(String ambariViewsStormInstance) {
		this.ambariViewsStormInstance = ambariViewsStormInstance;
	}

	public String getAmbariViewsYARNViewName() {
		return ambariViewsYARNViewName;
	}

	public void setAmbariViewsYARNViewName(String ambariViewsYARNViewName) {
		this.ambariViewsYARNViewName = ambariViewsYARNViewName;
	}

	public String getAmbariViewsYARNVersion() {
		return ambariViewsYARNVersion;
	}

	public void setAmbariViewsYARNVersion(String ambariViewsYARNVersion) {
		this.ambariViewsYARNVersion = ambariViewsYARNVersion;
	}

	public String getAmbariViewsYARNInstance() {
		return ambariViewsYARNInstance;
	}

	public void setAmbariViewsYARNInstance(String ambariViewsYARNInstance) {
		this.ambariViewsYARNInstance = ambariViewsYARNInstance;
	}

	public String getHiveHost() {
		return hiveHost;
	}

	public void setHiveHost(String hiveHost) {
		this.hiveHost = hiveHost;
	}

	public String getHivePort() {
		return hivePort;
	}

	public void setHivePort(String hivePort) {
		this.hivePort = hivePort;
	}

	public String getHiveAdminPrincipal() {
		return hiveAdminPrincipal;
	}

	public void setHiveAdminPrincipal(String hiveAdminPrincipal) {
		this.hiveAdminPrincipal = hiveAdminPrincipal;
	}

	public String getHiveAdminKeytab() {
		return hiveAdminKeytab;
	}

	public void setHiveAdminKeytab(String hiveAdminKeytab) {
		this.hiveAdminKeytab = hiveAdminKeytab;
	}

	public String getHdfsAdminPrinciple() {
		return hdfsAdminPrinciple;
	}

	public void setHdfsAdminPrinciple(String hdfsAdminPrinciple) {
		this.hdfsAdminPrinciple = hdfsAdminPrinciple;
	}

	public String getHdfsAdminKeytab() {
		return hdfsAdminKeytab;
	}

	public void setHdfsAdminKeytab(String hdfsAdminKeytab) {
		this.hdfsAdminKeytab = hdfsAdminKeytab;
	}

	public String getHdfsRestrictionBeginswith() {
		return hdfsRestrictionBeginswith;
	}

	public void setHdfsRestrictionBeginswith(String hdfsRestrictionBeginswith) {
		this.hdfsRestrictionBeginswith = hdfsRestrictionBeginswith;
	}

	public String getHiveConnectionString() {
		return hiveConnectionString;
	}

	public void setHiveConnectionString(String hiveConnectionString) {
		this.hiveConnectionString = hiveConnectionString;
	}

	public String getZooKeeperQuoram() {
		return zooKeeperQuoram;
	}

	public void setZooKeeperQuoram(String zooKeeperQuoram) {
		this.zooKeeperQuoram = zooKeeperQuoram;
	}

	public String getKafkaQuoram() {
		return kafkaQuoram;
	}

	public void setKafkaQuoram(String kafkaQuoram) {
		this.kafkaQuoram = kafkaQuoram;
	}

	public String getKafkaJAASPath() {
		return kafkaJAASPath;
	}

	public void setKafkaJAASPath(String kafkaJAASPath) {
		this.kafkaJAASPath = kafkaJAASPath;
	}

	public String getUagHostname() {
		return uagHostname;
	}

	public void setUagHostname(String uagHostname) {
		this.uagHostname = uagHostname;
	}

	public String getKeyPath() {
		return keyPath;
	}

	public void setKeyPath(String keyPath) {
		this.keyPath = keyPath;
	}

	public String getUagUser() {
		return uagUser;
	}

	public void setUagUser(String uagUser) {
		this.uagUser = uagUser;
	}

	public String getEdgeRestrictionBeginswith() {
		return edgeRestrictionBeginswith;
	}

	public void setEdgeRestrictionBeginswith(String edgeRestrictionBeginswith) {
		this.edgeRestrictionBeginswith = edgeRestrictionBeginswith;
	}

	public String getLog4j() {
		return log4j;
	}

	public void setLog4j(String log4j) {
		this.log4j = log4j;
	}

	public String getDbhost() {
		return dbhost;
	}

	public void setDbhost(String dbhost) {
		this.dbhost = dbhost;
	}

	public String getDbport() {
		return dbport;
	}

	public void setDbport(String dbport) {
		this.dbport = dbport;
	}

	public String getDbdriverclass() {
		return dbdriverclass;
	}

	public void setDbdriverclass(String dbdriverclass) {
		this.dbdriverclass = dbdriverclass;
	}

	public String getDbname() {
		return dbname;
	}

	public void setDbname(String dbname) {
		this.dbname = dbname;
	}

	public String getDbuser() {
		return dbuser;
	}

	public void setDbuser(String dbuser) {
		this.dbuser = dbuser;
	}

	public String getDbpassword() {
		return dbpassword;
	}

	public void setDbpassword(String dbpassword) {
		this.dbpassword = dbpassword;
	}

	@Override
	public String toString() {
		return "ReadConfiguration [workDir=" + workDir + ", krb5Location=" + krb5Location + ", rangerHost=" + rangerHost
				+ ", rangerPort=" + rangerPort + ", rangerAdmin=" + rangerAdmin + ", rangerAdminPassword="
				+ rangerAdminPassword + ", ambariHost=" + ambariHost + ", ambariPort=" + ambariPort
				+ ", ambariAdminUsr=" + ambariAdminUsr + ", ambariAdminPwd=" + ambariAdminPwd + ", ambariClusterName="
				+ ambariClusterName + ", ambariViewsHost=" + ambariViewsHost + ", ambariViewsPort=" + ambariViewsPort
				+ ", ambariViewsAdminUsr=" + ambariViewsAdminUsr + ", ambariViewsAdminPwd=" + ambariViewsAdminPwd
				+ ", ambariViewsFileViewName=" + ambariViewsFileViewName + ", ambariViewsFileVersion="
				+ ambariViewsFileVersion + ", ambariViewsFileInstance=" + ambariViewsFileInstance
				+ ", ambariViewsHive1ViewName=" + ambariViewsHive1ViewName + ", ambariViewsHive1Version="
				+ ambariViewsHive1Version + ", ambariViewsHive1Instance=" + ambariViewsHive1Instance
				+ ", ambariViewsHive2ViewName=" + ambariViewsHive2ViewName + ", ambariViewsHive2Version="
				+ ambariViewsHive2Version + ", ambariViewsHive2Instance=" + ambariViewsHive2Instance
				+ ", ambariViewsTezViewName=" + ambariViewsTezViewName + ", ambariViewsTezVersion="
				+ ambariViewsTezVersion + ", ambariViewsTezInstance=" + ambariViewsTezInstance
				+ ", ambariViewsPigViewName=" + ambariViewsPigViewName + ", ambariViewsPigVersion="
				+ ambariViewsPigVersion + ", ambariViewsPigInstance=" + ambariViewsPigInstance
				+ ", ambariViewsOozieeViewName=" + ambariViewsOozieeViewName + ", ambariViewsOozieeVersion="
				+ ambariViewsOozieeVersion + ", ambariViewsOozieeInstance=" + ambariViewsOozieeInstance
				+ ", ambariViewsStormViewName=" + ambariViewsStormViewName + ", ambariViewsStormVersion="
				+ ambariViewsStormVersion + ", ambariViewsStormInstance=" + ambariViewsStormInstance
				+ ", ambariViewsYARNViewName=" + ambariViewsYARNViewName + ", ambariViewsYARNVersion="
				+ ambariViewsYARNVersion + ", ambariViewsYARNInstance=" + ambariViewsYARNInstance + ", hiveHost="
				+ hiveHost + ", hivePort=" + hivePort + ", hiveAdminPrincipal=" + hiveAdminPrincipal
				+ ", hiveAdminKeytab=" + hiveAdminKeytab + ", hiveConnectionString=" + hiveConnectionString
				+ ", zooKeeperQuoram=" + zooKeeperQuoram + ", hdfsAdminPrinciple=" + hdfsAdminPrinciple
				+ ", hdfsAdminKeytab=" + hdfsAdminKeytab + ", hdfsRestrictionBeginswith=" + hdfsRestrictionBeginswith
				+ ", kafkaQuoram=" + kafkaQuoram + ", kafkaJAASPath=" + kafkaJAASPath + ", uagHostname=" + uagHostname
				+ ", keyPath=" + keyPath + ", uagUser=" + uagUser + ", edgeRestrictionBeginswith="
				+ edgeRestrictionBeginswith + ", log4j=" + log4j + ", dbhost=" + dbhost + ", dbport=" + dbport
				+ ", dbdriverclass=" + dbdriverclass + ", dbname=" + dbname + ", dbuser=" + dbuser + ", dbpassword="
				+ dbpassword + "]";
	}

	public ReadConfiguration() {
		Properties prop = new Properties();
		try {
			//prop.load(new FileInputStream("C:\\Users\\hedindi\\git\\acinonyx\\conf\\config.ini"));
			prop.load(new FileInputStream("/usr/acinonyx/conf/dev/config.ini"));
			this.setWorkDir(prop.getProperty("working.directory").trim());
			this.setKrb5Location(prop.getProperty("krb5.conf.location").trim());

			this.setRangerHost(prop.getProperty("ranger.host").trim());
			this.setRangerPort(prop.getProperty("ranger.port").trim());
			this.setRangerAdmin(prop.getProperty("ranger.admin.user").trim());
			this.setRangerAdminPassword(prop.getProperty("ranger.admin.password").trim());

			this.setAmbariHost(prop.getProperty("ambari.host").trim());
			this.setAmbariPort(prop.getProperty("ambari.port").trim());
			this.setAmbariAdminUsr(prop.getProperty("ambari.admin.usr").trim());
			this.setAmbariAdminPwd(prop.getProperty("ambari.admin.pwd").trim());
			this.setAmbariClusterName(prop.getProperty("ambari.cluster.name").trim());

			this.setAmbariViewsHost(prop.getProperty("ambari.views.host").trim());
			this.setAmbariViewsPort(prop.getProperty("ambari.views.port").trim());
			this.setAmbariViewsAdminUsr(prop.getProperty("ambari.views.admin.usr").trim());
			this.setAmbariViewsAdminPwd(prop.getProperty("ambari.views.admin.pwd").trim());
			this.setAmbariViewsFileViewName(prop.getProperty("ambari.views.file.viewname").trim());
			this.setAmbariViewsFileVersion(prop.getProperty("ambari.views.file.version").trim());
			this.setAmbariViewsFileInstance(prop.getProperty("ambari.views.file.instance").trim());
			this.setAmbariViewsHive1ViewName(prop.getProperty("ambari.views.hive1.viewname").trim());
			this.setAmbariViewsHive1Version(prop.getProperty("ambari.views.hive1.version").trim());
			this.setAmbariViewsHive1Instance(prop.getProperty("ambari.views.hive1.instance").trim());
			this.setAmbariViewsHive2ViewName(prop.getProperty("ambari.views.hive2.viewname").trim());
			this.setAmbariViewsHive2Version(prop.getProperty("ambari.views.hive2.version").trim());
			this.setAmbariViewsHive2Instance(prop.getProperty("ambari.views.hive2.instance").trim());
			this.setAmbariViewsTezViewName(prop.getProperty("ambari.views.tez.viewname").trim());
			this.setAmbariViewsTezVersion(prop.getProperty("ambari.views.tez.version").trim());
			this.setAmbariViewsTezInstance(prop.getProperty("ambari.views.tez.instance").trim());

			this.setAmbariViewsPigViewName(prop.getProperty("ambari.views.pig.viewname").trim());
			this.setAmbariViewsPigVersion(prop.getProperty("ambari.views.pig.version").trim());
			this.setAmbariViewsPigInstance(prop.getProperty("ambari.views.pig.instance").trim());

			this.setAmbariViewsOozieeViewName(prop.getProperty("ambari.views.oozie.viewname").trim());
			this.setAmbariViewsOozieeVersion(prop.getProperty("ambari.views.oozie.version").trim());
			this.setAmbariViewsOozieeInstance(prop.getProperty("ambari.views.oozie.instance").trim());

			this.setAmbariViewsStormViewName(prop.getProperty("ambari.views.storm.viewname").trim());
			this.setAmbariViewsStormVersion(prop.getProperty("ambari.views.storm.version").trim());
			this.setAmbariViewsStormInstance(prop.getProperty("ambari.views.storm.instance").trim());

			this.setAmbariViewsYARNViewName(prop.getProperty("ambari.views.yarn.viewname").trim());
			this.setAmbariViewsYARNVersion(prop.getProperty("ambari.views.yarn.version").trim());
			this.setAmbariViewsYARNInstance(prop.getProperty("ambari.views.yarn.instance").trim());

			this.setHdfsAdminPrinciple(prop.getProperty("hdfs.admin.principle").trim());
			this.setHdfsAdminKeytab(prop.getProperty("hdfs.admin.keytab").trim());
			this.setHdfsRestrictionBeginswith(prop.getProperty("hdfs.restriction.beginswith").trim());

			this.setHiveAdminKeytab(prop.getProperty("hive.admin.keytab").trim());
			this.setHiveAdminPrincipal(prop.getProperty("hive.admin.principle").trim());
			this.setHiveHost(prop.getProperty("hive.host").trim());
			this.setHivePort(prop.getProperty("hive.port").trim());
			this.setHiveConnectionString(prop.getProperty("hive.connection.string").trim());
			this.setZooKeeperQuoram(prop.getProperty("zookeeper.quoram").trim());

			this.setKafkaQuoram(prop.getProperty("kafka.zookeeper.quoram").trim());
			this.setKafkaJAASPath(prop.getProperty("kafka.auth.jaas").trim());

			this.setUagHostname(prop.getProperty("uag.hostname").trim());
			this.setKeyPath(prop.getProperty("uag.pkey.path").trim());
			this.setUagUser(prop.getProperty("uag.user").trim());
			this.setEdgeRestrictionBeginswith(prop.getProperty("uag.restriction.beginswith").trim());

			this.setLog4j(prop.getProperty("log4j.config").trim());

			this.setDbdriverclass(prop.getProperty("db.driverclass").trim());
			this.setDbhost(prop.getProperty("db.host").trim());
			this.setDbname(prop.getProperty("db.name").trim());
			this.setDbpassword(prop.getProperty("db.password").trim());
			this.setDbport(prop.getProperty("db.port").trim());
			this.setDbuser(prop.getProperty("db.user").trim());
			
			this.setSmtphost(prop.getProperty("smtp.host").trim());
			this.setSmtpport(prop.getProperty("smtp.port").trim());
			this.setSmtpsender(prop.getProperty("smtp.sender").trim());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
