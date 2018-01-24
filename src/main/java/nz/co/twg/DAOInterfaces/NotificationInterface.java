package nz.co.twg.DAOInterfaces;

public interface NotificationInterface {
    String getToEmail(String location, String condition);
    String getFromEmail(String location, String condition);
    String getEmailBody(String location, String condition);
    String getFromEmailPassword(String location, String condition);
}
