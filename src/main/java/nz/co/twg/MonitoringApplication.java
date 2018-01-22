package nz.co.twg;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.lang.*;

@EnableAutoConfiguration
@SpringBootApplication
public class MonitoringApplication {
    private static final Logger LOG = LoggerFactory.getLogger(MonitoringApplication.class);

    public static void main( String[] args )
    {
        SpringApplication.run(MonitoringApplication.class, args);

        yamlBean bean = new yamlBean();

        String yamlFileLocation = "src/main/resources/";
        String yamlFileName = "monitoringProperties.yaml";

        
        try {
            String yamlData = readSnakeYAML(yamlFileLocation+yamlFileName).toString();
            System.out.println("after ToString"+ yamlData);
            String[] yamlSplit = yamlData.replaceAll("\\{","").replaceAll("\\}","").split(",");
            System.out.println("print " + yamlSplit[0]);

            bean.setFolder(yamlSplit[0]);
            bean.setFileLimit(yamlSplit[1]);
            bean.setAgeLimit(yamlSplit[2]);
            bean.setToEmail(yamlSplit[3]);
            bean.setFromEmail(yamlSplit[4]);
            bean.setEmailContent(yamlSplit[5]);

            System.out.println(bean.getFileLimit());
            System.out.println(bean.getAgeLimit());
            System.out.println(bean.getToEmail());
            System.out.println(bean.getFromEmail());
            System.out.println(bean.getEmailContent());

        } catch (IOException exception) {
            LOG.error("Cannot read YAML File");
        }
    }

    private static Object readSnakeYAML(String yamlFile) throws IOException {
        Yaml yamlObject = new Yaml();
        InputStream input = new FileInputStream(new File(yamlFile));
        Iterable<Object> yam = yamlObject.loadAll(input);
        for (Object data : yam){
            return data;
        }
        return null;
    }


    private static int numberOfFiles(String dir) throws IOException {
        File directory = new File(dir);
        return directory.list().length;

    }

    private static File[] fileDate(String dir) {
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
