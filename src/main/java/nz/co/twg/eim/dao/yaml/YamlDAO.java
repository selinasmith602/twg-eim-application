package nz.co.twg.eim.dao.yaml;

import nz.co.twg.eim.dao.DAO;
import nz.co.twg.eim.model.EimObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yaml.snakeyaml.Yaml;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

abstract class YamlDAO<T extends EimObject> implements DAO<T> {
    protected final Logger log = LoggerFactory.getLogger(this.getClass());
    private final InputStream yamlInputStream;

    private Map<String, T> myList;

    public YamlDAO(String yamlFile) throws FileNotFoundException {
        this(new FileInputStream(yamlFile));
    }

    public YamlDAO(InputStream yamlInputStream) {
        this.yamlInputStream = yamlInputStream;
    }

    @Override
    public Collection<T> list() {
        if (myList == null) {
            myList = new LinkedHashMap<>();
            Yaml yamlObject = new Yaml();
            try {
                for (Object data : yamlObject.loadAll(yamlInputStream)) {
                    T convert = convert((Map<String, ?>) data);
                    if (convert != null && !myList.containsKey(convert.getId())) {
                        myList.put(convert.getId(), convert);
                    } else {
                        log.error(convert + " already exists or result is null");
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return myList.values();
    }

    abstract protected T convert(Map<String, ?> m);

    @Override
    public T get(String id) {
        if (myList == null) {
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
