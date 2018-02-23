package nz.co.twg.eim.dao.yaml;

import nz.co.twg.eim.model.notification.EmailNotification;
import nz.co.twg.eim.model.notification.Notification;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.util.Map;

@Component
public class NotificationDAO extends YamlDAO<Notification>{

    public NotificationDAO(@Value("${yaml.notifications.source}") String yamlFile) throws FileNotFoundException {
        super(yamlFile);
    }

    @Value("${email.host}")
    private String smtpConnectionString;

    @Override
    protected Notification convert(Map<String, ?> m) {
        String id = m.keySet().iterator().next();
        Map<String, ?> notifyValues = (Map<String, ?>)m.values().iterator().next();
        try {
            if ("email".equals(notifyValues.get("type"))) {
                return new EmailNotification(id, (String)notifyValues.get("fromEmail"), (String)notifyValues.get("toEmail"), (String)notifyValues.get("emailSubject"), (String)notifyValues.get("emailBody"), smtpConnectionString);
            }
        } catch (Exception e) {
            log.error(id + " has invalid configuration");
        }
        return null;
    }
}