package nz.co.twg.eim.dao.yaml;

import nz.co.twg.eim.model.action.Action;
import nz.co.twg.eim.model.action.StandardAction;

import java.util.Map;

public class ActionDAO extends YamlDAO<Action> {

    public ActionDAO(String yamlFile) {
        super(yamlFile);
    }

    @Override
    protected StandardAction convert(Map<String, ?> m) {
        String id = m.keySet().iterator().next();
        Map<String, ?> condValues = (Map<String, ?>)m.values().iterator().next();
        if ("file".equals(condValues.get("type"))) {
            return new StandardAction(id ,(String)condValues.get("condition"),(String)condValues.get("notification"),(String)condValues.get("schedule"));
        } else {
            return null;
        }
    }
}
