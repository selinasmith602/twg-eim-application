package nz.co.twg.eim.dao;

import java.util.Map;

public interface ConditionInterface {
    String getFolderLocation(String location, String condition);
    String getFileLimit(String location, String condition);
    String getFileAge(String location, String condition);
    String getCondition(Map<String, String> map);
}
