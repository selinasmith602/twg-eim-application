package nz.co.twg.model;

import java.io.File;
import java.util.List;

public class FolderCheckCondition implements Condition<List<File>> {
    public ConditionResult<List<File>> check(Action checkingAction) {
        return new ConditionResult<List<File>>() {
            public List<File> getPayload() {
                return null;
            }

            public boolean shouldFire() {
                return false;
            }
        };
    }
}
