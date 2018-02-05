package nz.co.twg;
import nz.co.twg.DAOInterfaces.ActionInterface;
import nz.co.twg.DAOInterfaces.SchedulerInterface;
import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

public class CronTriggerClass implements SchedulerInterface {

    public void scheduler(String cronNum) throws Exception{
        //cronNum ="0 24 14 * * ?";//"0/5 * * * * ?" ; //0 0/5 14,15 * * ?
        String key = "Time";

        JobDetail job = JobBuilder.newJob(TestJob.class)
                .withIdentity("Testing", "group1").build();


        Trigger trigger = TriggerBuilder
                .newTrigger()
                .withIdentity(key, "group1")
                .withSchedule(
                        CronScheduleBuilder.cronSchedule(cronNum))
                .build();

        //schedule it
        Scheduler scheduler = new StdSchedulerFactory().getScheduler();
        scheduler.start();
        scheduler.scheduleJob(job, trigger);

    }
}