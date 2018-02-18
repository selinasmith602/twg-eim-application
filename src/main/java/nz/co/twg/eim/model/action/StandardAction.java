package nz.co.twg.eim.model.action;

import nz.co.twg.eim.MonitoringApplication;
import nz.co.twg.eim.model.condition.Condition;
import nz.co.twg.eim.model.condition.ConditionResult;
import nz.co.twg.eim.model.condition.FileCondition;
import nz.co.twg.eim.model.notification.Notification;
import nz.co.twg.eim.dao.yaml.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.io.*;

public class StandardAction implements Action {

    private String id;
    private List<Condition> condition;
    private List<Notification> notification;
    private String cronConfig;

    public String getId() {
        return id;
    }

    public StandardAction(String id, List<Condition> condition, List<Notification> notification, String cronConfig) {
        this.id = id;
        this.condition = condition;
        this.notification = notification;
        this.cronConfig = cronConfig;
    }

    @Override
    public List<Condition> getConditions() {
        return condition;
    }

    @Override
    public List<Notification> getNotifications() {
        return notification;
    }

    public void execute() throws ActionExecutionException {
        MonitoringApplication.LOG.info("executing ");
        System.out.println(getConditions());
        for (Condition f:getConditions()) {
            System.out.println(getConditions().iterator().next().getId());
        };
        System.out.println(getConditions().iterator().next().getMaxAge());

        System.out.println("retrieved");




        /*
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
*/
    }
}
