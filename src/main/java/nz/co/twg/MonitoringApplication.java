package nz.co.twg;

import nz.co.twg.DAOInterfaces.DAOInt;
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
public class MonitoringApplication {
    public static final Logger LOG = LoggerFactory.getLogger(MonitoringApplication.class);

    public static void main( String[] args )
    {
        SpringApplication.run(MonitoringApplication.class, args);

        DAOInt props = new YAMLDAO();
        System.out.println(props.getFolderLocation("src/main/resources/monitoringProperties.yaml", "Condition1"));
        System.out.println(props.getFileLimit("src/main/resources/monitoringProperties.yaml", "Condition2"));
        System.out.println(props.getFileAge("src/main/resources/monitoringProperties.yaml", "Condition1"));
        System.out.println(props.getToEmail("src/main/resources/monitoringProperties.yaml", "Condition1"));
        System.out.println(props.getFromEmail("src/main/resources/monitoringProperties.yaml", "Condition1"));
        System.out.println(props.getFromEmailPassword("src/main/resources/monitoringProperties.yaml", "Condition1"));
        System.out.println(props.getEmailBody("src/main/resources/monitoringProperties.yaml", "Condition1"));
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
