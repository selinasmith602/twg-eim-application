package nz.co.twg.eim.dao.yaml;

import nz.co.twg.eim.model.condition.Condition;
import nz.co.twg.eim.model.condition.FileCondition;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ConditionDAO extends YamlDAO<Condition> {

    public ConditionDAO(String yamlFile) {
        super(yamlFile);
    }

    @Override
    protected Condition convert(Map<String, ?> m) {
        String id = m.keySet().iterator().next();
        Map<String, ?> condValues = (Map<String, ?>)m.values().iterator().next();
        if ("file".equals(condValues.get("type"))) {
            FileCondition f = new FileCondition("123",5,1222,"dir");



            return new FileCondition(id ,(Integer)condValues.get("maxAge"),(Integer)condValues.get("maxFiles"),(String)condValues.get("directory"));
        } else {
            return null;
        }
    }
}
