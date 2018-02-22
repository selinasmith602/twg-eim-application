package nz.co.twg.eim;

import org.quartz.Job;
import org.quartz.core.QuartzScheduler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.*;


@EnableAutoConfiguration
@SpringBootApplication
public abstract class MonitoringApplication {
    private Logger log = LoggerFactory.getLogger(this.getClass());



    public static void main( String[] args )
    {
        SpringApplication.run(MonitoringApplication.class, args);
    }
}
