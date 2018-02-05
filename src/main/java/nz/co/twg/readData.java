package nz.co.twg;

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

    public static String readSnakeYAML(String path, String condition) throws IOException {
        Yaml yamlObject = new Yaml();
        InputStream input = new FileInputStream(new File(path));
        for (Object data : yamlObject.loadAll(input)) {
            String value = data.toString().replaceAll("\\=\\[", ": ").replaceAll("\\{", "").replaceAll("\\}", "").replaceAll("\\]", "");
            if (value.startsWith(condition)){

                return value;
            }
        }
        return ("Condition doesn't exist");
    }

    public static String readxml(String path, String condition) throws IOException {
        System.out.println(path);
        return path;
    }

}

