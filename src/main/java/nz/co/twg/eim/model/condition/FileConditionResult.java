package nz.co.twg.eim.model.condition;

import nz.co.twg.eim.model.condition.ConditionResult;

import java.io.File;
import java.util.List;

public class FileConditionResult implements ConditionResult<List<File>> {


    private List<File> payload;
    private boolean shouldFire;

    public FileConditionResult(List<File> payload, boolean shouldFire) {
        this.payload = payload;
        this.shouldFire = shouldFire;
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
                .reduce("", (fn, acc) -> acc + "\n" + fn);

    }
}
