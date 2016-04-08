package org.websocket.dao;

import org.hibernate.Session;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by Business_Book on 06.04.2016.
 */

@Stateless
public class AbstractManager {

    @PersistenceContext(unitName = "dbpostgres")
    protected EntityManager manager;

    public <T> T save(T object) {
        manager.persist(object);
        manager.flush();
        return object;
    }

    public <T> T update(T object) {
        object = manager.merge(object);
        manager.flush();
        return object;
    }

    public <T> void remove(T object) {
        manager.remove(object);
        manager.flush();
    }

    public <T, I> T find(T object, I objectId) {
        manager.flush();
        object = (T) manager.find(object.getClass(), objectId);
        return object;
    }

    public Session getSession() {
        return (Session) manager.getDelegate();
    }
}
