package org.websocket.dao;

import model.entity.User;
import org.apache.commons.collections.CollectionUtils;
import org.hibernate.Session;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

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

    public String normalizeStringForQuery(String source) {
        return source == null || "".equals(source.trim()) ? null : "%" + source.trim().toLowerCase() + "%";
    }

    public List getSafeResultList(Query query) {
        List result = query.getResultList();
        if(CollectionUtils.isEmpty(result)) {
            return null;
        }
        return result;
    }

    public User getSafeSingleResult(Query query) {
        List result = query.getResultList();
        if(CollectionUtils.isEmpty(result)) {
            return null;
        }
        return (User) result.get(0);
    }
}
