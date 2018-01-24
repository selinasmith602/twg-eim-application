package nz.co.twg.model;

public interface ConditionResult<T> {
    T getPayload();
    boolean shouldFire();
}
