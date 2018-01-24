package nz.co.twg;

import java.util.Date;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

public class TLSEmail {

    /**
     Outgoing Mail (SMTP) Server
     requires TLS or SSL: smtp.gmail.com (use authentication)
     TLSEmail is using TSL connection via Gmail SMTP
     Use Authentication: Yes
     Port for TLS/STARTTLS: 587
     */
    public static void main(String[] args) {

        final String fromEmail = "devtestingtwg@gmail.com"; // Sender email must be Gmail
        final String password = "discoduck"; // Password for Gmail account
        final String toEmail = "wan.loke@thewarehouse.co.nz"; // recipient email

        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        //create Authenticator object to pass in Session.getInstance argument
        Authenticator auth = new Authenticator() {
            //override the getPasswordAuthentication method
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, password);
            }
        };
        Session session = Session.getInstance(props, auth);
        sendEmail(session, toEmail,"Testing Email for Monitoring App", "Please check the file and order...");

    }

    /**
     * Utility method to send simple HTML email
     * @param session
     * @param toEmail
     * @param subject
     * @param body
     */

    public static void sendEmail(Session session, String toEmail, String subject, String body){
        try
        {
            MimeMessage message = new MimeMessage(session);

            message.addHeader("Content-type", "text/HTML; charset=UTF-8");
            message.addHeader("format", "flowed");
            message.addHeader("Content-Transfer-Encoding", "8bit");

            message.setFrom(new InternetAddress("devtestingtwg@gmail.com", "NoReply-MonitoringApp"));
            message.setReplyTo(InternetAddress.parse("wan.loke@thewarehouse.co.nz", false));
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