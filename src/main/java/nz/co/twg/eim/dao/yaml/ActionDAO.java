package nz.co.twg.eim.dao.yaml;

import nz.co.twg.eim.MonitoringApplication;
import nz.co.twg.eim.model.action.Action;
import nz.co.twg.eim.model.action.StandardAction;
import nz.co.twg.eim.model.condition.Condition;
import nz.co.twg.eim.model.notification.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class ActionDAO extends YamlDAO<Action> {

    @Autowired
    NotificationDAO notificationDAO;

    @Autowired
    ConditionDAO conditionDAO;


    public ActionDAO(@Value("${yaml.actions.source}") String yamlFile) throws FileNotFoundException {
        super(yamlFile);
    }

    @Override
    protected StandardAction convert(Map<String, ?> m) {
        String id = m.keySet().iterator().next();
        Map<String, ?> actionValues = (Map<String, ?>)m.values().iterator().next();
        Stream<String> conditionStream = ((List<String>) actionValues.get("conditions")).stream();
        List<Condition<?>> conditionList = conditionStream.map(conditionDAO::get).collect(Collectors.toList());
        Stream<String> notificationStream = ((List<String>) actionValues.get("notifications")).stream();
        List<Notification> notificationList = notificationStream.map(notificationDAO::get).collect(Collectors.toList());
        String schedule = (String) actionValues.get("schedule");

        try {
            return new StandardAction(id ,conditionList,notificationList, schedule);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(id + " has invalid configuration");
        }
    return null;
    }
}
