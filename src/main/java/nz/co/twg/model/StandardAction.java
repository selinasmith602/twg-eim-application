package nz.co.twg.model;

import org.yaml.snakeyaml.Yaml;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.io.*;

public class StandardAction implements Action {

    public String getId() {
        return null;
    }
    @Override
    public List<Condition<?>> getConditions() {
        List<Condition<?>> yamlCondition = new ArrayList<>();
       try {
            String yamlData = readSnakeYAML("src/main/resources/monitoringProperties.yaml").toString();
            System.out.println("after ToString" + yamlData);
            String[] yamlSplit = yamlData.replaceAll("\\{", "").replaceAll("\\}", "").split(",");
           /* for (int i = 0; i < yamlSplit.length; i++){
                yamlCondition.add(new Condition(yamlSplit[i]));
            }*/
            System.out.println("print " + yamlSplit[0]);
        } catch (IOException exception) {
            System.out.println("IO Exception thrown");
        }
        return yamlCondition;
    }

    private static Object readSnakeYAML(String yamlFile) throws IOException {
        Yaml yamlObject = new Yaml();
        InputStream input = new FileInputStream(new File(yamlFile));
        Iterable<Object> yam = yamlObject.loadAll(input);
        for (Object data : yam){
            System.out.println(data);

            //System.out.println(data.getClass());
            return data;
        }
        return yam;
    }
    @Override
    public List<Notification> getNotifications() {
        return null;
    }

    @Override
    public List<String> getCronConfigs() {
        return null;
    }

    public void execute() throws ActionExecutionException {
        Stream<Condition<?>> s = getConditions().stream();

        Stream<? extends ConditionResult<?>> results = s.map(c -> c.check(this));
        if(results.allMatch(c -> c.shouldFire())) {
            List<ConditionResult<?>> resultList = results.collect(Collectors.toList());
            Stream<Exception> exceptions = getNotifications() //
                    .stream() //
                    .map(n -> n.doNotify(resultList)) //
                    .filter(nr -> !nr.isNotified()).map(nr -> nr.getNotificationException());
            if(exceptions.findFirst().isPresent()) {
                throw new ActionExecutionException("Could not fire action " + getId(), exceptions.collect(Collectors.toList()));
            }
        }

    }
}
