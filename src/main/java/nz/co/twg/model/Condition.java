package nz.co.twg.model;

public interface Condition<T> {
    ConditionResult<T> check(Action checkingAction);
}
