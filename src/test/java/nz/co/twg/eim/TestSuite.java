package nz.co.twg.eim;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import nz.co.twg.eim.dao.yaml.*;
import static org.junit.Assert.assertEquals;
import java.util.Collection;

@SpringBootTest
public class TestSuite {

    @Test
    public void ListConditions() {
        ConditionDAO d = new ConditionDAO("src/test/resources/monitoringProperties.yaml");
        System.out.println(d.get("Condition1").getId());
    }

    @Test
    public void ListNotifications(){
        NotificationDAO d = new NotificationDAO("src/test/resources/notification.yaml");
        System.out.println(d.get("Notification1").getId());

    }

    @Test
    public void ListActions(){
        ActionDAO d = new ActionDAO("src/test/resources/conditionAction.yaml");
        System.out.println(d.get("Action1").getId());

    }

    @Test
    public void EmailNotification(){
        SmtpEmail email = new SmtpEmail();
        assertEquals(email.simpleEmail(), true);
    }

}
