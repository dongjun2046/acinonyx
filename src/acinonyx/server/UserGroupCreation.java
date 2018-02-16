package acinonyx.server;

import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.commons.mail.HtmlEmail;
import org.apache.commons.mail.DefaultAuthenticator;
import acinonyx.conf.ReadConfiguration;

public class UserGroupCreation {
	private static final String ALPHA_NUMERIC_STRING = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789:)(%$?";
	ReadConfiguration readConf = new ReadConfiguration();
	
	public List<String> createUsersGroup(String tid, List<String> userList, String gName, String hostName, String requestor){
		
		final Logger web_log = Logger.getLogger(UserGroupCreation.class);
		
		PropertyConfigurator.configure(readConf.getLog4j());

		String outMessage="";
		
		List<String> list = new ArrayList<String>();
		int retCode = 0;
		
		SSHExecute sse = new SSHExecute();
		
		if(!gName.matches("users")){
			retCode = sse.executeCommand(tid, "groupadd " + gName, hostName);
			if(0 == retCode){
				web_log.info(tid + " Successfully created the group: " + gName);	
				outMessage = "Successfully created the group: " + gName + "\n";
				list.add(outMessage);

			} else if(9 == retCode) {
				web_log.info(tid + " Group already exists: " + gName);	
				outMessage = "Group already exists: " + gName + "\n";
				list.add(outMessage);
			} else if(-1 == retCode) { 
				web_log.info("Error encountered while creating group");
			} else {
				web_log.error(tid + " Error while creating the group: " + gName + " Error Code : " + retCode + " Using default group : users");
				outMessage = "Error while creating the group: " + gName + " Error Code : " + retCode + " Using default group : users" + "\n";
				gName="users";
				list.add(outMessage);
			} 
		} 
		sse = null;
		sse = new SSHExecute();
		
		for (String uName : userList) {
			if(uName.matches("[a-z0-9_-]+") && uName.length()>0){
				String password = randomAlphaNumeric(15) ;
				retCode = sse.executeCommand(tid, "useradd -m -p $(openssl passwd -1 '" + password +"') -g " + gName + " " + uName , hostName);
				if(0 == retCode){
					web_log.info(tid + " Successfully created the user: " + uName + " and added to group: " + gName);	
					outMessage = "Successfully created the user: " + uName + " and added to group: " + gName +"\n";
					list.add(outMessage);
					sendEmail(hostName,uName,password,requestor);
				} else if(9 == retCode) {
					web_log.info(tid + " user already exists: " + gName);	
					outMessage = "user already exists: " + gName + "\n";
					list.add(outMessage);
					retCode = sse.executeCommand(tid, "usermod -G "+ gName + " " + uName, hostName);
				} else if(-1 == retCode) { 
					web_log.info("Error encountered while creating user");
				} else { 
					web_log.error(tid + " Error while creating the user: " + uName + " Error Code : " + retCode);
					outMessage = "Error while creating the user: " + uName + " Error Code : " + retCode + "\n";
					list.add(outMessage);
				} 					
			}
			else {
				web_log.error(tid + " Invalid Username format " + uName);
				outMessage = "Invalid Username format " + uName + "\n";
				list.add(outMessage);
			}
		} 
	
		return list;
	}
	
	public String randomAlphaNumeric(int count) {
		StringBuilder builder = new StringBuilder();
			while (count-- != 0) {
				int character = (int)(Math.random()*ALPHA_NUMERIC_STRING.length());
				builder.append(ALPHA_NUMERIC_STRING.charAt(character));
			}
		return builder.toString();
		}
	
	public boolean sendEmail(String server, String uName, String password, String emailAddress){
		final Logger web_log = Logger.getLogger(UserGroupCreation.class);
		PropertyConfigurator.configure(readConf.getLog4j());
		boolean status = false;
		HtmlEmail email =  new HtmlEmail();
		String email_html_msg= "Server : " + server + " <br />Username : " + uName + "<br />Password : " + password ;
        try{
	        email.setHostName(readConf.getSmtphost());
	        email.setSmtpPort(Integer.parseInt(readConf.getSmtpport()));
	        email.setStartTLSEnabled(false);
	        //email.setAuthenticator(new DefaultAuthenticator("eedc_hdp_s_p-anx", "password"));
	        email.setFrom(readConf.getSmtpsender());
	        email.setSubject("Password for " + uName);
	        email.setMsg(email_html_msg);
	        email.addTo(emailAddress);
	        email.send();
	        web_log.info("Sent password via email for user : " + uName);
        } catch (Exception e){
        	status = false;
        	web_log.error("Failed to sent password due to email connectivity issues : " + e);
        }
        return status;
	}
}

