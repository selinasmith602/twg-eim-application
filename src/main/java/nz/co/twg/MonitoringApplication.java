package nz.co.twg;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@EnableAutoConfiguration
@SpringBootApplication
public class MonitoringApplication {
    static final Logger LOG = LoggerFactory.getLogger(MonitoringApplication.class);

    public static void main( String[] args )
    {
        SpringApplication.run(MonitoringApplication.class, args);
        System.out.println("Hello world");
        LOG.info("Info Log Fired");
        LOG.warn("Warning Log Fired");
        LOG.error("Error Log Fired");
    }

}
