package nz.co.twg.eim;

import com.google.common.io.Files;
import nz.co.twg.eim.dao.yaml.ActionDAO;
import nz.co.twg.eim.dao.yaml.ConditionDAO;
import nz.co.twg.eim.dao.yaml.NotificationDAO;
import nz.co.twg.eim.model.condition.Condition;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.FileSystemUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;

import static org.assertj.core.api.Assertions.fail;
import static org.junit.Assert.assertEquals;

@SpringBootTest
public class TestSuite {
    String conditionFile = "src/test/resources/condition.yaml";
    String notificationFile = "src/test/resources/notification.yaml";
    String actionFile = "src/test/resources/conditionAction.yaml";
    static File tempDir;


    @BeforeClass
    public static void createTempFile() throws IOException {
        tempDir = Files.createTempDir();
        File old1 = new File(tempDir, "old1.txt");
        File old2 = new File(tempDir, "old2.txt");
        File recent1 = new File(tempDir, "recent1.txt");
        File recent2 = new File(tempDir, "recent2.txt");
        File current = new File(tempDir, "current.txt");
        old1.createNewFile();
        old2.createNewFile();
        recent1.createNewFile();
        recent2.createNewFile();
        current.createNewFile();


        old1.setLastModified((System.currentTimeMillis() - 10000000));
        old2.setLastModified(System.currentTimeMillis() - 90000000);
        recent1.setLastModified(System.currentTimeMillis() - 300000);
        recent2.setLastModified(System.currentTimeMillis() - 300000);

    }

    @Test
    public void fileDate() throws IOException {
        File directory = new File(tempDir.getAbsolutePath());
        File[] directory_contents = directory.listFiles();
        for (File f : directory_contents) {
            Date date = new Date(f.lastModified());
            System.out.println(date);
        }
    }


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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void EmailNotification() {
        SmtpEmail email = new SmtpEmail();
        assertEquals(email.simpleEmail(), true);
    }

    @Test
    public void ValidateReadNotification() throws FileNotFoundException {
        NotificationDAO d = new NotificationDAO(notificationFile);
        String testNotification = "Notification1";
        try {
            if (!d.get(testNotification).getId().isEmpty()) {
                assertEquals(d.get(testNotification).getId(), testNotification);
            }
        } catch (NullPointerException e) {
            fail("Notification " + testNotification + " couldnt be found in the source notification.");
        }
    }

    @Test
    public void ValidateReadNotification1() throws FileNotFoundException {


        NotificationDAO d = new NotificationDAO(notificationFile);
        String testNotification = "Notification6";
        try {
            if (!d.get(testNotification).getId().isEmpty()) {
                assertEquals(d.get(testNotification).getId(), testNotification);
            }
        } catch (NullPointerException e) {
            fail("Notification " + testNotification + " couldnt be found in the source notification.");
        }
    }

    @Test
    public void ValidateReadCondition() throws FileNotFoundException {
        ConditionDAO d = new ConditionDAO(conditionFile);
        String testCondition = "Condition1";
        try {
            if (!d.get(testCondition).getId().isEmpty()) {
                assertEquals(d.get(testCondition).getId(), testCondition);
            }
        } catch (NullPointerException e) {
            fail("Condition " + testCondition + " couldnt be found in the source conditions");
        }
    }

    @Test
    public void ValidateReadCondition1() throws FileNotFoundException {
        ConditionDAO d = new ConditionDAO(conditionFile);
        String testCondition = "Condition6";
        Condition<?> condition = d.get(testCondition);
        try {
            if (!condition.getId().isEmpty()) {
                assertEquals(d.get(testCondition).getId(), testCondition);
            }
        } catch (Exception e) {
            if (condition != null) {
                fail("Condition " + testCondition + " couldnt be found in the source conditions");
            }
        }
    }

    @Test
    public void ValidateReadAction() throws FileNotFoundException {
        ActionDAO d = getActionDAO();
        String testAction = "Action1";
        try {
            if (!d.get(testAction).getId().isEmpty()) {
                assertEquals(d.get(testAction).getId(), testAction);
            }
        } catch (NullPointerException e) {
            fail("Action " + testAction + " couldnt be found in the source action.");
        }
    }

    @Test
    public void ValidateReadAction1() throws FileNotFoundException {

        ActionDAO d = getActionDAO();
        String testAction = "Action6";
        try {
            if (!d.get(testAction).getId().isEmpty()) {
                assertEquals(d.get(testAction).getId(), testAction);
            }
        } catch (NullPointerException e) {
            fail("Action " + testAction + " couldnt be found in the source action.");
        }
    }

    private ActionDAO getActionDAO() throws FileNotFoundException {
        return new ActionDAO(actionFile) {
            {
                this.conditionDAO = new ConditionDAO(conditionFile);
                this.notificationDAO = new NotificationDAO(notificationFile);
            }
        };
    }

    @Test
    public void ValidateNumberOfCondAction() throws FileNotFoundException {
        ActionDAO d = getActionDAO();
        String testAction = "Action1";
        assertEquals(d.get(testAction).getConditions().isEmpty(), false);

    }

    @Test
    public void ValidateNumberOfNotiAction() throws FileNotFoundException {
        ActionDAO d = getActionDAO();
        String testAction = "Action1";
        assertEquals(d.get(testAction).getNotifications().isEmpty(), false);
    }


    @AfterClass
    public static void cleanupTest() {
        FileSystemUtils.deleteRecursively(tempDir);
    }

}
