package nz.co.twg.model;

import java.util.List;

public interface Notification extends EimObject {
    NotificationResult doNotify(List<ConditionResult<?>> conditionResults);
}
