package nz.co.twg.eim;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@EnableAutoConfiguration
@SpringBootApplication
public abstract class MonitoringApplication {
    private Logger log = LoggerFactory.getLogger(this.getClass());



    public static void main( String[] args )
    {
        SpringApplication.run(MonitoringApplication.class, args);
    }
}
