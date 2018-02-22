package nz.co.twg.eim.model.condition;

import nz.co.twg.eim.model.action.Action;
import nz.co.twg.eim.model.condition.Condition;
import nz.co.twg.eim.model.condition.ConditionResult;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PrimitiveIterator;

public abstract class FolderCheckCondition implements Condition<List<File>> {

    private String location;

    public ConditionResult<List<File>> check(Action checkingAction) {
        List<File> result = new ArrayList<>();

        return new FileConditionResult(result,!result.isEmpty());
    }

    @Override
    public String getId() {
        return null;
    }

}
