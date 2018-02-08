package nz.co.twg.eim.model.notification;

import nz.co.twg.eim.model.condition.ConditionResult;

import java.util.List;

public class FileNotification implements Notification {

    private final String id;
    private final String toEmail;
    private final String subject;
    private final String emailBody;

    public FileNotification(String id, String toEmail, String subject, String emailBody) {
        this.id = id;
        this.toEmail = toEmail;
        this.subject = subject;
        this.emailBody = emailBody;
    }

    @Override
    public NotificationResult doNotify(List<ConditionResult<?>> conditionResults) {
        return null;
    }

    @Override
    public String getId() {
        return id;
    }
}
