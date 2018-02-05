package nz.co.twg.model;

public interface Condition<T> extends EimObject{
    ConditionResult<T> check(Action checkingAction);

}
