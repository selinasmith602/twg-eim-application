package nz.co.twg.eim;

import com.google.common.io.Files;
import org.yaml.snakeyaml.Yaml;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class readData{
    public static String findFileType(String path, String condition) throws IOException {
        return FileType(path,condition);
    }
    public static String FileType(String path, String condition) throws IOException {
        String fileType = Files.getFileExtension(path);
        if (fileType.equals("yaml")) {
            return readSnakeYAML(path,condition);
        } else if (fileType.equals("xml")) {
            return readxml(path,condition);
        } else {
            System.out.println("Invalid File Type");
        }
        return null;
    }



}

