package nz.co.twg;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class TestJob implements Job {
    public void execute(JobExecutionContext context)
            throws JobExecutionException {

        System.out.println("this is working!?!");

    }

}