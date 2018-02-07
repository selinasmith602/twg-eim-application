package nz.co.twg.eim;

import org.quartz.Job;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.*;


@EnableAutoConfiguration
@SpringBootApplication
public abstract class MonitoringApplication implements Job{
    public static final Logger LOG = LoggerFactory.getLogger(MonitoringApplication.class);

    public static void main( String[] args )
    {
        SpringApplication.run(MonitoringApplication.class, args);
    }
}
