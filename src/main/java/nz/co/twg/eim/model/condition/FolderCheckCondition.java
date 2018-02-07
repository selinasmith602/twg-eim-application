package nz.co.twg.eim.model.condition;

import nz.co.twg.eim.model.action.Action;
import nz.co.twg.eim.model.condition.Condition;
import nz.co.twg.eim.model.condition.ConditionResult;

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

            @Override
            public String toMessage() {
                return null;
            }
        };
    }

    @Override
    public String getId() {
        return null;
    }
}
