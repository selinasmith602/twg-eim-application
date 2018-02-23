package nz.co.twg.eim.model.condition;

import nz.co.twg.eim.model.action.Action;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public abstract class FolderCheckCondition implements Condition<List<File>> {

    private String location;

    public ConditionResult<List<File>> check(Action checkingAction) {
        List<File> result = new ArrayList<>();

        return new FileConditionResult(this, result,!result.isEmpty());
    }

    @Override
    public String getId() {
        return null;
    }

}
