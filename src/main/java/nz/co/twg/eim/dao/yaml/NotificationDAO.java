package nz.co.twg.eim.dao.yaml;

import nz.co.twg.eim.model.notification.FileNotification;
import nz.co.twg.eim.model.notification.Notification;

import java.util.Map;

public class NotificationDAO extends YamlDAO<Notification>{

    public NotificationDAO(String yamlFile) {
        super(yamlFile);
    }

    @Override
    protected Notification convert(Map<String, ?> m) {
        String id = m.keySet().iterator().next();
        Map<String, ?> notifyValues = (Map<String, ?>)m.values().iterator().next();
        if ("email".equals(notifyValues.get("type"))) {
            return new FileNotification(id ,(String)notifyValues.get("toEmail"),(String)notifyValues.get("emailSubject"),(String)notifyValues.get("emailBody"));
        } else {
            return null;
        }
    }
}