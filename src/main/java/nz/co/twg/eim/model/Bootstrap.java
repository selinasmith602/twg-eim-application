package nz.co.twg.eim.model;

import nz.co.twg.eim.dao.yaml.ActionDAO;
import nz.co.twg.eim.model.action.Action;
import nz.co.twg.eim.sched.ActionScheduler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class Bootstrap {
    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    ActionDAO actionDAO;


    @Autowired
    private ActionScheduler sched;

    @PostConstruct
    public void init() {
        for (Action a : actionDAO.list()) {
            try {
                sched.scheduleAction(a);
                log.info("loaded action " + a.getId());
            } catch (Exception ex) {
                log.error("Could not schedule action " + a.getId());
            }
        }

    }
}
