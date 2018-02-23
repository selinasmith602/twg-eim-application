package nz.co.twg.eim.sched;

import nz.co.twg.eim.model.action.Action;

public interface ActionScheduler {

    void scheduleAction(Action action) throws ActionSchedulerException;
}
