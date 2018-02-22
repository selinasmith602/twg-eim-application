package nz.co.twg.eim.model.notification;

import nz.co.twg.eim.model.action.Action;
import nz.co.twg.eim.model.condition.ConditionResult;
import org.springframework.beans.factory.annotation.Value;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.List;
import java.util.Properties;

public class EmailNotification implements Notification {

    private final String id;
    private final String toEmail;
    private final String fromEmail;
    private final String subject;
    private final String emailBody;

    @Value("${email.host}")
    private String smtpConnectionString;
    private Session session;

    public EmailNotification(String id, String fromEmail, String toEmail, String subject, String emailBody) {
        this.id = id;
        this.fromEmail = fromEmail;
        this.toEmail = toEmail;
        this.subject = subject;
        this.emailBody = emailBody;
    }

    @Override
    public NotificationResult doNotify(List<ConditionResult<?>> conditionResults, Action action) {
        try {
            MimeMessage message = createMessage();
                    message.setSubject(subject.replace("${actonId}", action.getId()));
                    message.setText(emailBody.replace("${actionId}", action.getId()).replace(
                            "${conditionResults}",
                            conditionResults.stream()
                                    .map(fnr -> fnr.toMessage())
                                    .reduce("", (fnr, acc) -> acc + "\n" + fnr)));
            Transport.send(message);

            return new DefaultNotificationResult(true, null);
        } catch (Exception e) {
            return new DefaultNotificationResult(false, e);
        }
    }

    private MimeMessage createMessage() throws MessagingException {
        MimeMessage msg = new MimeMessage(getSession());
        //set message headers
        msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
        msg.addHeader("format", "flowed");
        msg.addHeader("Content-Transfer-Encoding", "8bit");

        msg.setFrom(new InternetAddress(fromEmail));
        msg.setReplyTo(InternetAddress.parse(fromEmail, false));
        msg.setSentDate(new Date());
        msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail, false));
        return msg;

    }

    private Session getSession() {
        if(session != null) {
            Properties props = System.getProperties();
            props.put("mail.smtp.host", smtpConnectionString);
            session = Session.getInstance(props, null);
        }
        return session;

    }

    @Override
    public String getId() {
        return id;
    }

    public String getToEmail(){
        return toEmail;
    }

    public String getSubject(){
        return subject;
    }

    public String getEmailBody(){
        return emailBody;
    }
}
