package nz.co.twg.eim;

import com.google.common.io.Files;
import nz.co.twg.eim.model.condition.Condition;
import nz.co.twg.eim.model.notification.FileNotification;
import org.apache.tomcat.jni.Time;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;
import org.springframework.boot.test.context.SpringBootTest;
import nz.co.twg.eim.dao.yaml.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@SpringBootTest
public class TestSuite {
    String conditionFile = "src/test/resources/condition.yaml";
    String notificationFile = "src/test/resources/notification.yaml";
    String actionFile = "src/test/resources/conditionAction.yaml";
    static File tempDir = Files.createTempDir();


    @BeforeClass
    public static void createTempFile () throws IOException{
        tempDir.mkdir();
        String dir = tempDir.getAbsolutePath();
        File old1 = new File(dir+"\\old1.txt");
        File old2 = new File(dir+"\\old2.txt");
        File recent1 = new File(dir+"\\recent1.txt");
        File recent2 = new File(dir+"\\recent2.txt");
        File current = new File(dir+"\\current.txt");
        old1.createNewFile();
        old2.createNewFile();
        recent1.createNewFile();
        recent2.createNewFile();
        current.createNewFile();


        old1.setLastModified((System.currentTimeMillis()-10000000));
        old2.setLastModified(System.currentTimeMillis()-90000000);
        recent1.setLastModified(System.currentTimeMillis()-300000);
        recent2.setLastModified(System.currentTimeMillis()-300000);

    }

    @Test
    public void fileDate() throws IOException {
        File directory = new File(tempDir.getAbsolutePath());
        File[] directory_contents = directory.listFiles();
        for (File f: directory_contents) {
            Date date = new Date(f.lastModified());
            System.out.println(date);
        }
    }


    @Test
    public void ListConditions() {
        ConditionDAO d = new ConditionDAO(conditionFile);
        System.out.println(d.get("Condition1").getId());
    }

    @Test
    public void ListNotifications() {
        NotificationDAO d = new NotificationDAO(notificationFile);
        System.out.println(d.get("Notification1").getId());
    }

    @Test
    public void ListActions(){
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
    public void ValidateNumberOfCondAction() {
        ActionDAO d = new ActionDAO(actionFile);
        String testAction = "Action1";
        assertEquals(d.get(testAction).getConditions().isEmpty(), false);

    }

    @Test
    public void ValidateNumberOfNotiAction() {
        ActionDAO d = new ActionDAO(actionFile);
        String testAction = "Action1";
        assertEquals(d.get(testAction).getNotifications().isEmpty(), false);
    }



    @AfterClass
    public static void cleanupTest(){
        tempDir.delete();
    }

}
