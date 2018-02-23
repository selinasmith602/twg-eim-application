package nz.co.twg.eim.sched.quartz;

import nz.co.twg.eim.model.action.Action;
import nz.co.twg.eim.sched.ActionScheduler;
import nz.co.twg.eim.sched.ActionSchedulerException;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;


@Component
public class QuartzActionScheduler implements ActionScheduler {

    private final Scheduler sched;

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    public QuartzActionScheduler() throws SchedulerException {
        sched = new StdSchedulerFactory().getScheduler();
        sched.start();
    }

    @Override
    public void scheduleAction(Action action) throws ActionSchedulerException {
        Trigger trigger = newTrigger()
                .withSchedule(CronScheduleBuilder.cronSchedule(action.getCronConfig()))
                .withIdentity("trigger-"+ action.getId())
                .build();
        JobDataMap jdm = new JobDataMap();
        jdm.put("action", action);

        JobDetail jd = newJob()
                .ofType(ActionJob.class)
                .storeDurably(false)
                .withIdentity("job-" + action.getId())
                .usingJobData(jdm)
                .build();

        try {
            sched.scheduleJob(jd,trigger);
        } catch (SchedulerException e) {
            log.error("could not schedule job for action " + action.getId());
            throw new ActionSchedulerException(e);
        }
    }
}
