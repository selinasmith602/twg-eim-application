package nz.co.twg.eim.model.condition;

import nz.co.twg.eim.model.action.Action;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class FileCondition implements Condition<List<File>> {

    private final String id;
    private final Optional<Long> maxAge;
    private final Optional<Integer> fileCount;
    private final String directory;

    public FileCondition(String id, String directory, Optional<Long> maxAge, Optional<Integer> fileCount) {
        this.id = id;
        this.directory = directory;
        this.maxAge = maxAge;
        this.fileCount = fileCount;
        if (!maxAge.isPresent() && !fileCount.isPresent()) {
            throw new IllegalArgumentException("neither maxAge nor fileCount are specified in condition " + id);
        }
    }

    @Override
    public ConditionResult<List<File>> check(Action checkingAction) {
        File folder = new File(directory);

        if (folder.isDirectory()) {
            List<File> fileList = Arrays.asList(folder.listFiles());
            boolean fileCountTriggers = fileList.size() < fileCount.orElse(Integer.MAX_VALUE);
            boolean fileAgeTriggers = fileList
                    .stream()
                    .anyMatch(file -> file.lastModified() < maxAge.orElse(Long.MAX_VALUE));
            if (fileCountTriggers || fileAgeTriggers) {
                return new FileConditionResult(this, fileList, true);
            }

        }
        return new FileConditionResult(this, null, false);
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getDirectory() {
        return directory;
    }

    @Override
    public Optional<Long> getMaxAge() {
        return maxAge;
    }

    @Override
    public Optional<Integer> getFileCount() {
        return fileCount;
    }

}
