package nz.co.twg.eim.model.action;

import nz.co.twg.eim.model.condition.Condition;
import nz.co.twg.eim.model.condition.ConditionResult;
import nz.co.twg.eim.model.notification.Notification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StandardAction implements Action {

    private final Logger log = LoggerFactory.getLogger(this.getClass());
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


        log.info("starting action " + getId());
       Stream<Condition<?>> s = getConditions().stream();

        Stream<ConditionResult<?>> results = s.map(c -> c.check(this));
        List<ConditionResult<?>> resultList = results.collect(Collectors.toList());
        if(resultList.stream().allMatch(c -> c.shouldFire())) {
            log.info("conditions met, firing notification");

            List<Throwable> exceptions = getNotifications() //
                    .stream() //
                    .map(n -> n.doNotify(resultList, this)) //
                    .filter(nr -> !nr.isNotified()).map(nr -> nr.getNotificationException()).collect(Collectors.toList());
            if(!exceptions.isEmpty()) {
                exceptions.stream().forEach(t -> log.error("something wrong", t));
                throw new ActionExecutionException("Could not fire action " + getId(), exceptions);
            }
        } else {
            log.info("conditions not met.");
        }
        log.info("exiting action " + getId());

    }

    @Override
    public String toString() {
        return "[" + getClass().getName() + "[conditions:" + conditions + ", notifications: " + notifications + ", cron scheduled: " + cronConfig + "]]";
    }

    @Override
    public String getCronConfig() {
        return cronConfig;
    }
}
