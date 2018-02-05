package nz.co.twg.DAOInterfaces;

import nz.co.twg.model.Action;
import nz.co.twg.model.Condition;
import nz.co.twg.model.Notification;

public interface DAOInt extends ActionInterface, ConditionInterface, NotificationInterface {
    Action getActionById(String id);

    Condition<?> getConditionById(String id);

    Notification getNotificationById(String id);

}
