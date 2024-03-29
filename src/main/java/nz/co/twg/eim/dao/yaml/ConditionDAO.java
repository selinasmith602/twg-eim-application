package nz.co.twg.eim.dao.yaml;

import nz.co.twg.eim.model.condition.Condition;
import nz.co.twg.eim.model.condition.FileCondition;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.util.Map;
import java.util.Optional;

@Component
public class ConditionDAO extends YamlDAO<Condition<?>> {

    public ConditionDAO(@Value("${yaml.conditions.source}") String yamlFile) throws FileNotFoundException {
        super(yamlFile);
    }

    @Override
    protected Condition<?> convert(Map<String, ?> m) {
        String id = m.keySet().iterator().next();
        Map<String, ?> condValues = (Map<String, ?>)m.values().iterator().next();
        try {
            if ("file".equals(condValues.get("type"))) {
               return new FileCondition(id ,(String)condValues.get("directory"), Optional.ofNullable(Long.valueOf(((Integer)condValues.get("maxAge")) * 1000L)) , Optional.ofNullable((Integer)condValues.get("maxFiles")));
            }
        } catch (Exception e){
           log.error(id + " has invalid configuration", e);
        }
    return null;
    }

}
