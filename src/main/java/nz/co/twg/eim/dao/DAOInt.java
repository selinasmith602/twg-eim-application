package nz.co.twg.eim.dao;

import nz.co.twg.eim.model.action.Action;
import nz.co.twg.eim.model.condition.Condition;
import nz.co.twg.eim.model.notification.Notification;

public interface DAOInt {
    Action getActionById(String id);

    Condition<?> getConditionById(String id);

    Notification getNotificationById(String id);

}
