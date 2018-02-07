package nz.co.twg.eim.dao.yaml;

import nz.co.twg.eim.model.condition.Condition;
import nz.co.twg.eim.model.condition.FileCondition;

import java.util.Map;

public class ConditionDAO extends YamlDAO<Condition> {

    public ConditionDAO(String yamlFile) {
        super(yamlFile);
    }

    @Override
    protected Condition convert(Map<String, ?> m) {
        if ("file".equals(m.get("type"))) {
            return new FileCondition(m.get("id"), )
        } else {
            return null;
        }
    }
}
