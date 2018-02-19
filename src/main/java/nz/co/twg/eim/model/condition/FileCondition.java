package nz.co.twg.eim.model.condition;

import nz.co.twg.eim.model.action.Action;
import java.util.Optional;
import java.io.File;
import java.util.List;

public class FileCondition implements Condition<List<File>> {

    private final String id;
    private final Optional<Integer> maxAge;
    private final Optional<Integer> fileCount;
    private final String directory;

    public FileCondition(String id, String directory, Optional<Integer> maxAge, Optional<Integer> fileCount) {
        this.id = id;
        this.directory = directory;
        this.maxAge = maxAge;
        this.fileCount = fileCount;
    }

    @Override
    public ConditionResult<List<File>> check(Action checkingAction) {
        return null;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getDirectory(){ return directory; }

    @Override
    public Optional<Integer> getMaxAge(){ return maxAge; }

    @Override
    public Optional<Integer> getFileCount(){ return fileCount; }

}
