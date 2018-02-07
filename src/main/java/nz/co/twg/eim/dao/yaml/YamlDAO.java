package nz.co.twg.eim.dao.yaml;

import nz.co.twg.eim.dao.DAO;
import nz.co.twg.eim.model.EimObject;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

abstract class  YamlDAO<T extends EimObject> implements DAO<T> {
    private final String yamlFile;
    private Map<String, T> myList;

    public YamlDAO(String yamlFile) {
        this.yamlFile = yamlFile;
    }

    @Override
    public Collection<T> list() {
        if(myList == null){
            myList = new LinkedHashMap<>();
            Yaml yamlObject = new Yaml();
            try {
                InputStream input = new FileInputStream(new File(yamlFile));
                for (Object data : yamlObject.loadAll(input)) {
                    T convert = convert((Map<String, ?>) data);
                   myList.put(convert.getId(),convert);
                }
            } catch (Exception e) {
                //log exception
                System.out.println("No yaml source file found");
            }
        }
        return myList.values();
    }

    abstract protected T convert (Map<String, ?> m);

    @Override
    public T get(String id) {
        if (myList == null){
            list();
        }
        return myList.get(id);
    }

    @Override
    public boolean update(T item) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean delete(T item) {
        throw new UnsupportedOperationException();
    }
}
