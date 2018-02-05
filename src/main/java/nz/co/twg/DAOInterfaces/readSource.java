package nz.co.twg.DAOInterfaces;

import java.io.IOException;
import java.util.Map;

public interface readSource {

    Map<String, String> readSnakeYaml(String location, String Condition) throws IOException;

}
