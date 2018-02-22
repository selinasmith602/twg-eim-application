package nz.co.twg.eim.model.action;

import nz.co.twg.eim.model.condition.Condition;
import nz.co.twg.eim.model.condition.ConditionResult;
import nz.co.twg.eim.model.notification.Notification;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StandardAction implements Action {

    private String id;
    private List<Condition<?>> conditions;
    private List<Notification> notifications;
    private String cronConfig;

    public String getId() {
        return id;
    }

    public StandardAction(String id, List<Condition<?>> conditions, List<Notification> notifications, String cronConfig) {
        this.id = id;
        this.conditions = conditions;
        this.notifications = notifications;
        this.cronConfig = cronConfig;
    }

    @Override
    public List<Condition<?>> getConditions() {
        return conditions;
    }

    @Override
    public List<Notification> getNotifications() {
        return notifications;
    }

    public void execute() throws ActionExecutionException {
        /*MonitoringApplication.LOG.info("executing ");
        Stream<Condition> b = getConditions().stream();
        System.out.println(b);
        System.out.println(getConditions());
        for (Condition f:getConditions()) {
            System.out.println(getConditions().iterator().next().getId());
        };
        System.out.println(getConditions().iterator().next().getMaxAge());

        System.out.println("retrieved");*/





       Stream<Condition<?>> s = getConditions().stream();

        Stream<ConditionResult<?>> results = s.map(c -> c.check(this));
        if(results.allMatch(c -> c.shouldFire())) {
            List<ConditionResult<?>> resultList = results.collect(Collectors.toList());
            Stream<Exception> exceptions = getNotifications() //
                    .stream() //
                    .map(n -> n.doNotify(resultList, this)) //
                    .filter(nr -> !nr.isNotified()).map(nr -> nr.getNotificationException());
            if(exceptions.findFirst().isPresent()) {
                throw new ActionExecutionException("Could not fire action " + getId(), exceptions.collect(Collectors.toList()));
            }
        }

    }

    @Override
    public String toString() {
        return "[" + getClass().getName() + "[conditions:" + conditions + ", notifications: " + notifications + ", cron scheduled: " + cronConfig + "]]";
    }
}
