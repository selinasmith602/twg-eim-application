package nz.co.twg.DAOInterfaces;

import java.util.Map;

public interface NotificationInterface {
    String getToEmail(String location, String condition);
    String getFromEmail(String location, String condition);
    String getFromEmailPassword(String location, String condition);
    String getEmailSubject(String fileLocation, String conditionName);
    String getEmailBody(String location, String condition);
    String getNotification(Map<String, String> map);
}
