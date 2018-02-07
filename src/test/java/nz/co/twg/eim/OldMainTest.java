package nz.co.twg.eim;

import com.google.common.io.Files;
import junit.framework.*;
import nz.co.twg.eim.dao.Condition;
import nz.co.twg.eim.dao.Notification;
import nz.co.twg.eim.dao.createMap;
import nz.co.twg.eim.sched.CronTriggerClass;
import org.junit.Test;
import org.yaml.snakeyaml.Yaml;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

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

    public static String readSnakeYAML(String path, String condition) throws IOException {
        Yaml yamlObject = new Yaml();
        InputStream input = new FileInputStream(new File(path));
        for (Object data : yamlObject.loadAll(input)) {
            String value = data.toString().replaceAll("\\=\\[", ": ").replaceAll("\\{", "").replaceAll("\\}", "").replaceAll("\\]", "");
            if (value.startsWith(condition)){

                return value;
            }
        }
        return ("Condition doesn't exist");
    }

    public static List<String> readYaml(String path) throws IOException {
        Yaml yamlObject = new Yaml();
        InputStream input = new FileInputStream(new File(path));
        for (Object data : yamlObject.loadAll(input)) {

            String value = data.toString().replaceAll("\\=\\[", ": ").replaceAll("\\{", "").replaceAll("\\}", "").replaceAll("\\]", "");
        }
    }


}
