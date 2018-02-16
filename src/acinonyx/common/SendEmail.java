package acinonyx.common;
import org.apache.commons.mail.HtmlEmail;
import acinonyx.db.*;
//import org.apache.commons.mail.DefaultAuthenticator;
public class SendEmail{
public static void main(String []args){


                HtmlEmail email =  new HtmlEmail();
                //String email_html_msg= "Username : " + uName + "<br />Password : " + password ;
        try{
                email.setHostName("mailhost.emea.svc.corpintra.net");//readConf.getSmtphost());
                email.setSmtpPort(25);
                email.setStartTLSEnabled(false);
                //email.setAuthenticator(new DefaultAuthenticator("eedc_hdp_s_p-anx", "pQvZ2.GfV_4aqJ.A0q_f"));
                email.setFrom("appalerts.dbdp@daimler.com");
                email.setSubject("Password for ");
                email.setMsg("appalerts.dbdp@daimler.com");//email_html_msg);
                email.addTo("hemant.dindi@daimler.com");
                //email.send();
        } catch (Exception e){
                
//System.out.println(e);
        	
e.printStackTrace();
        }
        OpsRequestsInfoStore oi = new OpsRequestsInfoStore();
    	System.out.println(oi.getEmail("hemant"));
}
}

