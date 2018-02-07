package nz.co.twg.eim.model.condition;

public interface ConditionResult<T> {
    T getPayload();
    boolean shouldFire();
    String toMessage();
}
