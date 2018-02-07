package nz.co.twg.eim.dao;

import java.io.IOException;
import java.util.Map;

public class Condition implements ConditionInterface {

    @Override
    public String getCondition(Map<String, String> map) {
        return map.get(" directory") + ", " + map.get("maxFiles")  + ", " + map.get("maxAge") ;
    }

    public String getFolderLocation(String path, String condition) {
        Map<String,String> propertiesMap;
        try {
            propertiesMap = createMap.readInfo(path,condition);
            return propertiesMap.get(" directory");
        } catch(IOException e){
            System.out.println("failed to get YAML file");
        } catch(NullPointerException e){
            System.out.println(condition+" Can't be found");
        }

        return null;
    }

    public String getFileLimit(String path, String condition) {
        Map<String,String> propertiesMap;
        try {
            propertiesMap = createMap.readInfo(path,condition);
            return propertiesMap.get("maxFiles");
        } catch(IOException e){
            System.out.println("failed to get YAML file");
        } catch(NullPointerException e){
            System.out.println(condition+" Can't be found");
        }
        return null;
    }

    public String getFileAge(String path, String condition) {
        Map<String,String> propertiesMap;
        try {
            propertiesMap = createMap.readInfo(path,condition);
            return propertiesMap.get("maxAge");
        } catch(IOException e){
            System.out.println("failed to get YAML file");
        } catch(NullPointerException e){
            System.out.println(condition+" Can't be found");
        }
        return null;
    }
}
