package nz.co.twg.eim.model.notification;

public interface NotificationResult {
    boolean isNotified();
    Exception getNotificationException();
}
