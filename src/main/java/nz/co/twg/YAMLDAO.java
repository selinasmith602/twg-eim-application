package nz.co.twg;

import nz.co.twg.DAOInterfaces.ActionInterface;
import nz.co.twg.DAOInterfaces.ConditionInterface;
import nz.co.twg.DAOInterfaces.DAOInt;
import nz.co.twg.DAOInterfaces.NotificationInterface;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import java.util.HashMap;
import java.util.Map;

import static nz.co.twg.MonitoringApplication.LOG;

public class YAMLDAO implements DAOInt {

    public static Map<String, String> readSnakeYAML(String yamlFile, String condition) throws IOException {
        Yaml yamlObject = new Yaml();
        InputStream input = new FileInputStream(new File(yamlFile));
        Map<String,String> conditionMap = new HashMap<String, String>();
        for (Object data : yamlObject.loadAll(input)){
            String[] value = data.toString().split("\\[");
            if (condition.equals(value[0].replaceAll("\\W",""))){
                for (String val : value[1].split(",")) {
                    String[] holder = val.split("=");
                    conditionMap.put(holder[0].replaceAll("\\W",""),holder[1].replaceAll("\\[","").replaceAll("\\]","").replaceAll("\\}",""));
                }
                return conditionMap;
            }
        }
        LOG.error("Condition " + condition + " not found in YAML file " + yamlFile);
        return null;
    }

    public String getFolderLocation(String loc, String condition) {
        Map<String,String> propertiesMap;
        try {
            propertiesMap = readSnakeYAML(loc,condition);
            return propertiesMap.get("directory");
        } catch(IOException e){
            System.out.println("failed to get YAML file");
        }
        return null;
    }

    public String getFileLimit(String loc, String condition) {
        Map<String,String> propertiesMap;
        try {
            propertiesMap = readSnakeYAML(loc,condition);
            return propertiesMap.get("maxFiles");
        } catch(IOException e){
            System.out.println("failed to get YAML file");
        }
        return null;
    }

    public String getFileAge(String loc, String condition) {
        Map<String,String> propertiesMap;
        try {
            propertiesMap = readSnakeYAML(loc,condition);
            return propertiesMap.get("maxAge");
        } catch(IOException e){
            System.out.println("failed to get YAML file");
        }
        return null;
    }

    public String getToEmail(String loc, String condition) {
        Map<String,String> propertiesMap;
        try {
            propertiesMap = readSnakeYAML(loc,condition);
            return propertiesMap.get("toEmail");
        } catch(IOException e){
            System.out.println("failed to get YAML file");
        }
        return null;
    }

    public String getFromEmail(String loc, String condition) {
        Map<String,String> propertiesMap;
        try {
            propertiesMap = readSnakeYAML(loc,condition);
            return propertiesMap.get("fromEmail");
        } catch(IOException e){
            System.out.println("failed to get YAML file");
        }
        return null;
    }

    public String getFromEmailPassword(String loc, String condition) {
        Map<String,String> propertiesMap;
        try {
            propertiesMap = readSnakeYAML(loc,condition);
            return propertiesMap.get("fromEmailPassword");
        } catch(IOException e){
            System.out.println("failed to get YAML file");
        }
        return null;
    }

    public String getEmailBody(String loc, String condition) {
        Map<String,String> propertiesMap;
        try {
            propertiesMap = readSnakeYAML(loc,condition);
            return propertiesMap.get("emailContent");
        } catch(IOException e){
            System.out.println("failed to get YAML file");
        }
        return null;
    }

}