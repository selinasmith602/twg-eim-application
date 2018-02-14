package nz.co.twg.eim;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import nz.co.twg.eim.dao.yaml.*;
import static org.junit.Assert.assertEquals;
import java.util.Collection;

@SpringBootTest
public class TestSuite {

    @Test
    public void ValidateConditionRead() {
        String testCondition = "Condition6";
        ConditionDAO d = new ConditionDAO("src/test/resources/condition.yaml");
        try {
            if (!d.get(testCondition).getId().isEmpty()){
                System.out.println(d.get(testCondition).getId() + " has been found in the source conditions");
            }
        } catch (Exception e) {
            MonitoringApplication.LOG.error("Condition " + testCondition + " couldnt be found in the source conditions");
        }
    }

    @Test
    public void ListNotifications(){
        NotificationDAO d = new NotificationDAO("src/test/resources/notification.yaml");
    }

    @Test
    public void ListActions(){
        ActionDAO d = new ActionDAO("src/test/resources/conditionAction.yaml");
        System.out.println(d.get("Action1").getId());
        try {
            d.get("Action1").execute();
        } catch (Exception e ) {
            e.printStackTrace();
        }


    }

    @Test
    public void EmailNotification(){
        SmtpEmail email = new SmtpEmail();
        assertEquals(email.simpleEmail(), true);
    }

}
