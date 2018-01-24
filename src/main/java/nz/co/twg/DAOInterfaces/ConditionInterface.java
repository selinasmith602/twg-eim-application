package nz.co.twg.DAOInterfaces;

public interface ConditionInterface {
    String getFolderLocation(String location, String condition);
    String getFileLimit(String location, String condition);
    String getFileAge(String location, String condition);

}
