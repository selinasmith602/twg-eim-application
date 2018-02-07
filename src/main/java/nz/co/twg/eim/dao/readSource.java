package nz.co.twg.eim.dao;

import java.io.IOException;
import java.util.Map;

public interface readSource {

    Map<String, String> readSnakeYaml(String location, String Condition) throws IOException;

}
