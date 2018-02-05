package nz.co.twg.DAOInterfaces;

import java.io.IOException;
import java.util.Map;

public class Notification implements NotificationInterface {

    public String getToEmail(String path, String condition) {
        Map<String,String> propertiesMap;
        try {
            propertiesMap = createMap.readInfo(path,condition);
            return propertiesMap.get(" toEmail");
        } catch(IOException e){
            System.out.println("failed to get YAML file");
        } catch(NullPointerException e){
            System.out.println(condition+" Can't be found");
        }
        return null;
    }

    public String getFromEmail(String path, String condition) {
        Map<String,String> propertiesMap;
        try {
            propertiesMap = createMap.readInfo(path,condition);
            return propertiesMap.get("fromEmail");
        } catch(IOException e){
            System.out.println("failed to get YAML file");
        } catch(NullPointerException e){
            System.out.println(condition+" Can't be found");
        }
        return null;
    }

    public String getFromEmailPassword(String path, String condition) {
        Map<String,String> propertiesMap;
        try {
            propertiesMap = createMap.readInfo(path,condition);
            return propertiesMap.get("fromEmailPassword");
        } catch(IOException e){
            System.out.println("failed to get YAML file");
        } catch(NullPointerException e){
            System.out.println(condition+" Can't be found");
        }
        return null;
    }

    public String getEmailSubject(String path, String condition) {
        Map<String,String> propertiesMap;
        try {
            propertiesMap = createMap.readInfo(path,condition);
            return propertiesMap.get("emailSubject");
        } catch(IOException e){
            System.out.println("failed to get YAML file");
        }
        return null;
    }

    public String getEmailBody(String path, String condition) {
        Map<String,String> propertiesMap;
        try {
            propertiesMap = createMap.readInfo(path,condition);
            return propertiesMap.get("emailContent");
        } catch(IOException e){
            System.out.println("failed to get YAML file");
        } catch(NullPointerException e){
            System.out.println(condition+" Can't be found");
        }
        return null;
    }

    @Override
    public String getNotification(Map<String, String> map) {
        return map.get(" toEmail") + ", " + map.get("fromEmail") + ", " + map.get("fromEmailPassword") + ", " + map.get("emailSubject") + ", " + map.get("emailContent");
    }
}
