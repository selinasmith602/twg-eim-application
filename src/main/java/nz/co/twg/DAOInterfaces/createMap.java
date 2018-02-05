package nz.co.twg.DAOInterfaces;

import com.google.common.base.Splitter;
import nz.co.twg.readData;
import java.io.IOException;
import java.util.Map;


public class createMap {
    public static Map<String, String> readInfo(String path, String condition) throws IOException {
        Object data = readData.findFileType(path,condition);
        String[] value = data.toString().split(":");
        return (Splitter.on(", ").withKeyValueSeparator("=").split(value[1]));
    }
}
