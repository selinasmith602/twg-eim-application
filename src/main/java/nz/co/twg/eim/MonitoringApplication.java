package nz.co.twg.eim;

import nz.co.twg.eim.dao.yaml.ActionDAO;
import nz.co.twg.eim.model.Bootstrap;
import nz.co.twg.eim.model.action.Action;
import nz.co.twg.eim.sched.ActionScheduler;
import org.quartz.Scheduler;
import org.quartz.impl.StdScheduler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;


@EnableAutoConfiguration
@SpringBootApplication
public abstract class MonitoringApplication {
    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    Bootstrap bootstrap;
    public static void main(String[] args) {
        SpringApplication.run(MonitoringApplication.class, args);
    }
}
