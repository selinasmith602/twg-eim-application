package nz.co.twg.eim.model.notification;

public class DefaultNotificationResult implements NotificationResult {
    private final boolean notified;
    private final Exception notificationException;

    public DefaultNotificationResult(boolean notified, Exception notificationException) {
        this.notified = notified;
        this.notificationException = notificationException;
    }

    @Override
    public boolean isNotified() {
        return notified;
    }

    @Override
    public Exception getNotificationException() {
        return notificationException;
    }
}
