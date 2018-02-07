package nz.co.twg.eim.model.condition;

import nz.co.twg.eim.model.EimObject;
import nz.co.twg.eim.model.action.Action;

public interface Condition<T> extends EimObject {
    ConditionResult<T> check(Action checkingAction);

}
