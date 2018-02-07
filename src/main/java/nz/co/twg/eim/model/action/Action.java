package nz.co.twg.eim.model.action;

import nz.co.twg.eim.model.condition.Condition;
import nz.co.twg.eim.model.EimObject;
import nz.co.twg.eim.model.notification.Notification;

import java.util.List;

public interface Action extends EimObject {
    List<Condition<?>> getConditions();

    List<Notification> getNotifications();

    List<String> getCronConfigs();

    void execute() throws ActionExecutionException;
}
