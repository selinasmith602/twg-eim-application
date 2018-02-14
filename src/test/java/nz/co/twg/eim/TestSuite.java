package nz.co.twg.eim;

import nz.co.twg.eim.model.notification.FileNotification;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import nz.co.twg.eim.dao.yaml.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.Collection;

@SpringBootTest
public class TestSuite {
    String conditionFile = "src/test/resources/monitoringProperties.yaml";
    String notificationFile = "src/test/resources/notification.yaml";
    String actionFile = "src/test/resources/conditionAction.yaml";

    @Test
    public void ListConditions() {
        ConditionDAO d = new ConditionDAO(conditionFile);
        System.out.println(d.get("Condition1").getId());
    }

    @Test
    public void ListNotifications(){
        NotificationDAO d = new NotificationDAO(notificationFile);
        System.out.println(d.get("Notification1").getId());

    }

    @Test
    public void ListActions(){
        ActionDAO d = new ActionDAO(actionFile);
        System.out.println(d.get("Action1").getId());

    }

    @Test
    public void EmailNotification(){
        SmtpEmail email = new SmtpEmail();
        assertEquals(email.simpleEmail(), true);
    }

    @Test
    public void ValidateReadNotification() {
        NotificationDAO d = new NotificationDAO(notificationFile);
        String testNotification = "Notification1";
        try {
            if (!d.get(testNotification).getId().isEmpty()){
                assertEquals(d.get(testNotification).getId(), testNotification);
            }
        } catch (NullPointerException e) {
            MonitoringApplication.LOG.error("Notification " + testNotification + " couldnt be found in the source notification.");
        }
    }

    @Test
    public void ValidateReadNotification1() {
        NotificationDAO d = new NotificationDAO(notificationFile);
        String testNotification = "Notification6";
        try {
            if (!d.get(testNotification).getId().isEmpty()){
                assertEquals(d.get(testNotification).getId(), testNotification);
            }
        } catch (NullPointerException e) {
            MonitoringApplication.LOG.error("Notification " + testNotification + " couldnt be found in the source notification.");
        }
    }

    @Test
    public void ValidateReadCondition() {
        ConditionDAO d = new ConditionDAO(conditionFile);
        String testCondition = "Condition1";
        try {
            if (!d.get(testCondition).getId().isEmpty()){
                assertEquals(d.get(testCondition).getId(), testCondition);
        }
        } catch (NullPointerException e) {
            MonitoringApplication.LOG.error("Condition " + testCondition + " couldnt be found in the source conditions");
        }
    }

    @Test
    public void ValidateReadCondition1() {
        ConditionDAO d = new ConditionDAO(conditionFile);
        String testCondition = "Condition6";
        try {
            if (!d.get(testCondition).getId().isEmpty()){
                assertEquals(d.get(testCondition).getId(), testCondition);
            }
        } catch (Exception e) {
            MonitoringApplication.LOG.error("Condition " + testCondition + " couldnt be found in the source conditions");
        }
    }

    @Test
    public void ValidateReadAction() {
        ActionDAO d = new ActionDAO(actionFile);
        String testAction = "Action1";
        try {
            if (!d.get(testAction).getId().isEmpty()){
                assertEquals(d.get(testAction).getId(), testAction);
            }
        } catch (NullPointerException e) {
            MonitoringApplication.LOG.error("Action " + testAction + " couldnt be found in the source action.");
        }
    }

    @Test
    public void ValidateReadAction1() {
        ActionDAO d = new ActionDAO(actionFile);
        String testAction = "Action6";
        try {
            if (!d.get(testAction).getId().isEmpty()){
                assertEquals(d.get(testAction).getId(), testAction);
            }
        } catch (NullPointerException e) {
            MonitoringApplication.LOG.error("Action " + testAction + " couldnt be found in the source action.");
        }
    }
}
