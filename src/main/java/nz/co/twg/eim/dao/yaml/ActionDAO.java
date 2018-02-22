package nz.co.twg.eim.dao.yaml;

import nz.co.twg.eim.MonitoringApplication;
import nz.co.twg.eim.model.action.Action;
import nz.co.twg.eim.model.action.StandardAction;
import nz.co.twg.eim.model.condition.Condition;
import nz.co.twg.eim.model.notification.Notification;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@EnableAutoConfiguration
public class ActionDAO extends YamlDAO<Action> {

    public ActionDAO(String yamlFile) {
        super(yamlFile);
    }

    @Override
    protected StandardAction convert(Map<String, ?> m) {
        String id = m.keySet().iterator().next();
        Map<String, ?> condValues = (Map<String, ?>)m.values().iterator().next();
        List<Condition> listCon = new ArrayList<>();
        List<Notification> listNot = new ArrayList<>();
        for (String s:condValues.get("condition").toString().split(",")) {
            String c = s.replaceAll("\\[","").replaceAll("\\]","").replaceAll(" ","");
            ConditionDAO d = new ConditionDAO("src/test/resources/condition.yaml");
            listCon.add(d.get(c));
        }
        for (String nots:condValues.get("notification").toString().split(",")) {
            String c = nots.replaceAll("\\[","").replaceAll("\\]","").replaceAll(" ","");

            NotificationDAO notif = new NotificationDAO("src/test/resources/notification.yaml");
            listNot.add(notif.get(c));
        }

        try {
            return new StandardAction(id ,listCon,listNot,(String)condValues.get("schedule"));
        } catch (Exception e) {
            e.printStackTrace();
            MonitoringApplication.LOG.error(id + " has invalid configuration");
        }
    return null;
    }
}
