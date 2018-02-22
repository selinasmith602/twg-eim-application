package nz.co.twg.eim;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import nz.co.twg.eim.dao.yaml.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.io.FileNotFoundException;

@SpringBootTest
public class TestSuite {
    String conditionFile = "src/test/resources/condition.yaml";
    String notificationFile = "src/test/resources/notification.yaml";
    String actionFile = "src/test/resources/conditionAction.yaml";
    @Test
    public void ListConditions() throws FileNotFoundException {
        ConditionDAO d = new ConditionDAO(conditionFile);
        System.out.println(d.get("Condition1").getId());
    }

    @Test
    public void ListNotifications() throws FileNotFoundException {
        NotificationDAO d = new NotificationDAO(notificationFile);
        System.out.println(d.get("Notification1").getId());
    }

    @Test
    public void ListActions() throws FileNotFoundException {
        ActionDAO d = new ActionDAO(actionFile);
        try {
            d.get("Action1").execute();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void EmailNotification(){
        SmtpEmail email = new SmtpEmail();
        assertEquals(email.simpleEmail(), true);
    }

    @Test
    public void ValidateReadNotification() throws FileNotFoundException {
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
    public void ValidateReadNotification1() throws FileNotFoundException {


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
    public void ValidateReadCondition() throws FileNotFoundException {
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
    public void ValidateReadCondition1() throws FileNotFoundException {
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
    public void ValidateReadAction() throws FileNotFoundException {
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
    public void ValidateReadAction1() throws FileNotFoundException {
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

    @Test
    public void ValidateNumberOfCondAction() throws FileNotFoundException {
        ActionDAO d = new ActionDAO(actionFile);
        String testAction = "Action1";
        assertEquals(d.get(testAction).getConditions().isEmpty(), false);

    }

    @Test
    public void ValidateNumberOfNotiAction() throws FileNotFoundException {
        ActionDAO d = new ActionDAO(actionFile);
        String testAction = "Action1";
        assertEquals(d.get(testAction).getNotifications().isEmpty(), false);
    }
}
