package nz.co.twg.DAOInterfaces;

import java.util.Map;

public interface ConditionInterface {
    String getFolderLocation(String location, String condition);
    String getFileLimit(String location, String condition);
    String getFileAge(String location, String condition);
    String getCondition(Map<String, String> map);
}
