package nz.co.twg.eim.model.condition;

import java.io.File;
import java.util.List;

public class FileConditionResult implements ConditionResult<List<File>> {


    private final List<File> payload;
    private final boolean shouldFire;

    final Condition<List<File>> condition;

    public FileConditionResult(Condition<List<File>> condition, List<File> payload, boolean shouldFire) {
        this.payload = payload;
        this.shouldFire = shouldFire;
        this.condition = condition;
    }

    @Override
    public List<File> getPayload() {
        return payload;
    }

    @Override
    public boolean shouldFire() {
        return shouldFire;
    }

    @Override
    public String toMessage() {

        return payload.stream() //
                .map(file -> file.getAbsolutePath()) //
                .reduce("Condition id: " + condition.getId() + ":", (fn, acc) -> acc + "\n" + fn);
    }

    @Override
    public Condition<List<File>> getCondition() {
        return condition;
    }
}
