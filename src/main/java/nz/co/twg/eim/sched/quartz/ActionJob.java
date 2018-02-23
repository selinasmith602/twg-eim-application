package nz.co.twg.eim.sched.quartz;

import nz.co.twg.eim.model.action.Action;
import nz.co.twg.eim.model.action.ActionExecutionException;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ActionJob implements Job {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    private Action action;

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        try {
            log.debug("Executing action " + action.getId());
            getAction().execute();
            log.debug("Done executing action " + action.getId());
        } catch (ActionExecutionException e) {
            log.error("The action " + getAction().getId() + "failed to execute", e);
            throw new JobExecutionException(e);
        }
    }
}
