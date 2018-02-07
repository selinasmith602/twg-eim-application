package nz.co.twg.eim.dao;

import java.util.Collection;
import java.util.Map;

abstract class  YamlDAO<T> implements DAO<T> {
    private final String yamlFile;
    private Map<String, T> myList;

    public YamlDAO(String yamlFile) {
        this.yamlFile = yamlFile;
    }

    @Override
    public Collection<T> list() {
        if(myList == null){
            
        }
        return myList;
    }

    abstract protected <T> Convert (Map<String, ?> m);

}
