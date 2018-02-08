package nz.co.twg.eim;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import nz.co.twg.eim.dao.yaml.*;

import java.util.Collection;

@SpringBootTest
public class TestSuite {

    @Test
    public void ListConditions(){
        ConditionDAO d = new ConditionDAO("src/test/resources/monitoringProperties.yaml");
        Collection val = d.list();
    }

    @Test
    public void ListNotifications(){
        NotificationDAO d = new NotificationDAO("src/test/resources/notification.yaml");
        Collection val = d.list();
    }
}
