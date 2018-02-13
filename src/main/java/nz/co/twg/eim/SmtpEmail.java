package nz.co.twg.eim;

import java.util.Date;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SmtpEmail {
    private static String senderEmail = "DONOTREPLY@thewarehouse.co.nz";
    private static String userEmail = "wan.loke@thewarehouse.co.nz";
    private static String subjectLine = "Testing testing";
    private static String emailBodyContent = "Message sent!";
    private static String smtpHostServer = "smtp.thewarehousegroup.net";

    public static void simpleEmail() {
        Properties props = System.getProperties();
        props.put("mail.smtp.host", smtpHostServer);
        Session session = Session.getInstance(props, null);
        SmtpEmail.sendEmail(session, userEmail ,subjectLine, emailBodyContent);
    }

    public static void sendEmail(Session session, String userEmail, String subject, String body){
        try
        {
            MimeMessage msg = new MimeMessage(session);
            //set message headers
            msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
            msg.addHeader("format", "flowed");
            msg.addHeader("Content-Transfer-Encoding", "8bit");

            msg.setFrom(new InternetAddress(senderEmail, "DoNotReply-TWG"));
            msg.setReplyTo(InternetAddress.parse(senderEmail, false));
            msg.setSubject(subject, "UTF-8");
            msg.setText(body, "UTF-8");
            msg.setSentDate(new Date());
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(userEmail, false));
            System.out.println("Message is ready");
            Transport.send(msg);

            System.out.println("Email Sent Successfully!!");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

}
