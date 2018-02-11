package nz.co.twg.eim.model.action;

import nz.co.twg.eim.model.condition.Condition;
import nz.co.twg.eim.model.condition.ConditionResult;
import nz.co.twg.eim.model.notification.Notification;
import nz.co.twg.eim.dao.yaml.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.io.*;

public class StandardAction implements Action {

    private String id;
    private String condition;
    private String notification;
    private String cronConfig;

    public String getId() {
        return id;
    }

    public StandardAction(String id, String condition, String notification, String cronConfig) {
        this.id = id;
        this.condition = condition;
        this.notification = notification;
        this.cronConfig = cronConfig;
    }

    @Override
    public List<Condition<?>> getConditions() {
        String locationYaml = "src/test/resources/monitoringProperties.yaml";
        ConditionDAO d = new ConditionDAO(locationYaml);
        return (List)d.list();




        //--------------------------------------------------------------------------------
       /* List<Condition<?>> yamlCondition = new ArrayList<>();
       try {
            String yamlData = readSnakeYAML("src/main/resources/monitoringProperties.yaml").toString();
            System.out.println("after ToString" + yamlData);
            String[] yamlSplit = yamlData.replaceAll("\\{", "").replaceAll("\\}", "").split(",");
            System.out.println("print " + yamlSplit[0]);
        } catch (IOException exception) {
            System.out.println("IO Exception thrown");
        }*/

    }

    @Override
    public List<Notification> getNotifications() {
        String locationYaml = "src/test/resources/monitoringProperties.yaml";
        NotificationDAO d = new NotificationDAO(locationYaml);
        return (List)d.list();
    }

    @Override
    public List<String> getCronConfigs() {
        return null;
    }

    public void execute() throws ActionExecutionException {
        Stream<Condition<?>> s = getConditions().stream();

        Stream<? extends ConditionResult<?>> results = s.map(c -> c.check(this));
        if(results.allMatch(c -> c.shouldFire())) {
            List<ConditionResult<?>> resultList = results.collect(Collectors.toList());
            Stream<Exception> exceptions = getNotifications() //
                    .stream() //
                    .map(n -> n.doNotify(resultList)) //
                    .filter(nr -> !nr.isNotified()).map(nr -> nr.getNotificationException());
            if(exceptions.findFirst().isPresent()) {
                throw new ActionExecutionException("Could not fire action " + getId(), exceptions.collect(Collectors.toList()));
            }
        }

    }
}
