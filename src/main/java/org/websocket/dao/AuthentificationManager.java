package org.websocket.dao;

import model.entity.User;
import org.apache.log4j.Logger;

import javax.ejb.Stateless;
import javax.persistence.Query;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Business_Book on 04.04.2016.
 */

@Stateless
public class AuthentificationManager extends AbstractManager implements Serializable {

    private static final Logger logger = Logger.getLogger(AuthentificationManager.class);

    public AuthentificationManager() {
    }

    public User getUserByLoginPassword(String login, String password) {
        Query query = manager.createQuery("select u from User u where u.login = :login and u.password = :password ");
        query.setParameter("login", login);
        query.setParameter("password", password);
        List result = query.getResultList();
        if(result != null && result.size() > 0) {
            return (User) query.getResultList().get(0);
        }
        return null;
    }
}
