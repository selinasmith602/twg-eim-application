package nz.co.twg.eim.dao;

import com.google.common.base.Splitter;
import nz.co.twg.eim.MonitoringApplication;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

public class readSourceCondition implements readSource {


    public Map<String, String> readSnakeYaml(String location, String Condition)throws IOException {
        Yaml yamlObject = new Yaml();
        InputStream input = new FileInputStream(new File(location));

        for (Object data : yamlObject.loadAll(input)){
            String[] value = data.toString().split("\\[");
            if (Condition.equals(value[0].replaceAll("\\W",""))){
                Map<String, String> conditionMap = Splitter.on(", ").withKeyValueSeparator("=").split(value[1].replaceAll("\\{","").replaceAll("\\}","").replaceAll("\\]",""));
                return conditionMap;
            }
        }
        MonitoringApplication.LOG.error("Condition " + Condition + " not found in YAML file " + location);
        return null;
    }
}
