package nz.co.twg;

import com.google.common.io.Files;
import junit.framework.*;
import nz.co.twg.DAOInterfaces.Condition;
import nz.co.twg.DAOInterfaces.Notification;
import nz.co.twg.DAOInterfaces.createMap;
import org.junit.Test;
import org.springframework.boot.test.context.TestComponent;


import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Map;

public class OldMainTest extends TestCase{

    public String testGetDirectory(){
        /*Map<String,String> propertiesMap;
        try {
            propertiesMap = YAMLDAO.readSnakeYAML("src/test/resources/monitoringProperties.yaml","Condition1");
            assertEquals(propertiesMap.get("directory"),"src/");
        } catch(IOException e){
            System.out.println("failed to get YAML file");
        }*/
        return null;
    }

    @Test
    public void oldMainMethod() {
        Condition conditionInfo = new Condition();
        Notification notificationInfo = new Notification();

        String fileLocation = "src/test/resources/monitoringProperties.yaml";
        String notificationLoc = "src/test/resources/notification.yaml";
        String conditionName = "Notification1";
        String extension = Files.getFileExtension(fileLocation);
        System.out.println(extension);

        TLSEmail sendOutEmailNotification = new TLSEmail();
        sendOutEmailNotification.emailNotification(notificationLoc, conditionName);
        try{
            CronTriggerClass schedulerPleaseFire = new CronTriggerClass();
            schedulerPleaseFire.scheduler("0 4 15 * * ?");
        } catch(Exception e){
            System.out.println("not working");
        }


        try {
            System.out.println("Call the condition method: " + conditionInfo.getCondition(createMap.readInfo("src/main/resources/monitoringProperties.yaml", "Condition1")));
            System.out.println("Call the notification method: "+ notificationInfo.getNotification(createMap.readInfo("src/main/resources/notification.yaml", "Notification1")));
        } catch(IOException e) {
            System.out.println("failed to get condition");
        }

    }

    @Test
    public void testingOldMethods() {
        String resourcesDir = "src/test/resources";

        try {
            assertTrue("More than one file expected", numberOfFiles(resourcesDir) > 1);
            assertTrue("more than one file expected", fileDate(resourcesDir).length > 1);
        } catch (IOException e) {
            fail("oould not open directory " + resourcesDir);
        }
    }

    public static int numberOfFiles(String dir) throws IOException {
        File directory = new File(dir);
        return directory.list().length;

    }

    public static File[] fileDate(String dir) throws IOException{
        File directory = new File(dir);
        File[] directory_contents = directory.listFiles();
            for (File f: directory_contents) {
                Date date = new Date(f.lastModified());
                System.out.println(date);
            }
        return directory_contents;

    }


}
