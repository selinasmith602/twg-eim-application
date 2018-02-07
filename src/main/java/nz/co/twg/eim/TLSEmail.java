package nz.co.twg.eim;

import java.util.Date;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

/**
 Outgoing Mail (SMTP) Server
 requires TLS or SSL: smtp.gmail.com (use authentication)
 TLSEmail is using TSL connection via Gmail SMTP
 Use Authentication: Yes
 Port for TLS/STARTTLS: 587
 */


public class TLSEmail {

    /*NotificationInterface retrieveInfo = new Notification();
    final String fromEmail = retrieveInfo.getFromEmail(fileLocation, conditionName);
    final String fromEmailPassword = retrieveInfo.getFromEmailPassword(fileLocation, conditionName);
    final String toEmail = retrieveInfo.getToEmail(fileLocation, conditionName);
    String subjectLine = retrieveInfo.getEmailSubject(fileLocation, conditionName);
    String emailBodyContent = retrieveInfo.getEmailBody(fileLocation, conditionName);*/

    private String senderEmail = "devtestingtwg@gmail.com";
    private String senderEmailPassword = "discoduck";
    private String userEmail = "wan.loke@thewarehouse.co.nz";
    private String subjectLine = "Testing testing";
    private String emailBodyContent = "Message sent!";
    private String host = "smtp.gmail.com";
    private int port=587;
    private boolean auth=true;
    private boolean starttls = true;

    private Session getSession(){
        Properties setUpProps = new Properties();
        setUpProps.put("mail.smtp.host", host);
        setUpProps.put("mail.smtp.port", port);
        setUpProps.put("mail.smtp.auth", auth);
        setUpProps.put("mail.smtp.starttls.enable", starttls);
        //create Authenticator object to pass in Session.getInstance argument
        Authenticator auth = new Authenticator() {
            //override the getPasswordAuthentication method
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(senderEmail, senderEmailPassword);
            }
        };
        Session session = Session.getInstance(setUpProps, auth);
        return session;
    }

    public void emailNotification(String fileLocation, String conditionName){
        sendEmail(userEmail,subjectLine, emailBodyContent);
    }

    /**
     * Utility method to send simple HTML email
     * @param toEmail
     * @param subject
     * @param body
     */

    public void sendEmail(String toEmail, String subject, String body){
        try
        {
            MimeMessage message = new MimeMessage(getSession());

            message.addHeader("Content-type", "text/HTML; charset=UTF-8");
            message.addHeader("format", "flowed");
            message.addHeader("Content-Transfer-Encoding", "8bit");

            message.setFrom(new InternetAddress(senderEmail, "NoReply-MonitoringApp"));
            message.setReplyTo(InternetAddress.parse(userEmail, false));
            message.setSubject(subject, "UTF-8");
            message.setText(body, "UTF-8");
            message.setSentDate(new Date());
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail, false));
            System.out.println("Message is ready to be sent out");
            Transport.send(message);

            System.out.println("Email has successfully sent to the recipient!!");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }


}