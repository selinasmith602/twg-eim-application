package nz.co.twg.eim.model.condition;

import nz.co.twg.eim.model.action.Action;

import java.io.File;
import java.util.List;

public class FileCondition implements Condition<List<File>> {

    private final String id;
    private final int maxAge;
    private final int fileCount;
    private final String directory;

    public FileCondition(String id, int maxAge, int fileCount, String directory) {
        this.id = id;
        this.maxAge = maxAge;
        this.fileCount = fileCount;
        this.directory = directory;
    }

    @Override
    public ConditionResult<List<File>> check(Action checkingAction) {
        return null;
    }

    @Override
    public String getId() {
        return id;
    }
}
