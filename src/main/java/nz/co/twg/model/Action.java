package nz.co.twg.model;

import java.util.List;

public interface Action extends EimObject {
    List<Condition<?>> getConditions();

    List<Notification> getNotifications();

    List<String> getCronConfigs();

    void execute() throws ActionExecutionException;
}
