package nz.co.twg.eim.model.notification;

import nz.co.twg.eim.model.EimObject;
import nz.co.twg.eim.model.action.Action;
import nz.co.twg.eim.model.condition.ConditionResult;

import java.util.List;

public interface Notification extends EimObject {
    NotificationResult doNotify(List<ConditionResult<?>> conditionResults, Action action);

    String getToEmail();
    String getSubject();
    String getEmailBody();
}
