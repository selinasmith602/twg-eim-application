package nz.co.twg;

import com.google.common.io.Files;
import nz.co.twg.DAOInterfaces.Condition;
import nz.co.twg.DAOInterfaces.Notification;
import nz.co.twg.DAOInterfaces.createMap;
import org.quartz.Job;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.lang.*;


@EnableAutoConfiguration
@SpringBootApplication
public abstract class MonitoringApplication implements Job{
    public static final Logger LOG = LoggerFactory.getLogger(MonitoringApplication.class);

    public static void main( String[] args )
    {
        SpringApplication.run(MonitoringApplication.class, args);
        Condition conditionInfo = new Condition();
        Notification notificationInfo = new Notification();

        String fileLocation = "src/main/resources/monitoringProperties.yaml";
        String notificationLoc = "src/main/resources/notification.yaml";
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


    public static int numberOfFiles(String dir) throws IOException {
        File directory = new File(dir);
        return directory.list().length;

    }

    public static File[] fileDate(String dir) {
        File directory = new File(dir);
        File[] directory_contents = directory.listFiles();
        try {
            for (File f: directory_contents) {
                Date date = new Date(f.lastModified());
                System.out.println(date);
            }
        } catch (Exception exception){
            LOG.error("Failed to read directory " + dir);
        }
        return directory_contents;

    }

}
