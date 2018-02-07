package nz.co.twg.eim.dao;

import nz.co.twg.eim.model.EimObject;

import java.util.Collection;

public interface DAO<T extends EimObject> {
    Collection<T> list();
    T get(String id);
    boolean update(T item);
    boolean delete(T item);

}
