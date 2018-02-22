package nz.co.twg.eim;

import nz.co.twg.eim.dao.yaml.ActionDAO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MonitoringApplication.class)
public class TestYAML {

    @Value("${yaml.conditions.source}")
    String test;

    @Autowired
    ActionDAO actionDAO;

    @Test
    public void createAction() {
        System.out.println("Test:" + test);
        System.out.println("Test action = " + actionDAO.list());
    }
}
