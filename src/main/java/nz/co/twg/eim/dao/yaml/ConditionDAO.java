package nz.co.twg.eim.dao.yaml;

import nz.co.twg.eim.MonitoringApplication;
import nz.co.twg.eim.model.condition.Condition;
import nz.co.twg.eim.model.condition.FileCondition;
import java.util.Optional;
import java.util.Map;

public class ConditionDAO extends YamlDAO<Condition> {

    public ConditionDAO(String yamlFile) {
        super(yamlFile);
    }

    @Override
    protected Condition convert(Map<String, ?> m) {
        String id = m.keySet().iterator().next();
        Map<String, ?> condValues = (Map<String, ?>)m.values().iterator().next();
        try {
            if ("file".equals(condValues.get("type"))) {
               return new FileCondition(id ,(String)condValues.get("directory"), Optional.ofNullable((Integer)condValues.get("maxAge")) , Optional.ofNullable((Integer)condValues.get("maxFiles")));
            }
        } catch (Exception e){
            MonitoringApplication.LOG.error(id + " has invalid configuration");
        }
    return null;
    }

}
