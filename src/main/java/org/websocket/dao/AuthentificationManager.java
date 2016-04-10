package org.websocket.dao;

import model.entity.User;
import org.apache.log4j.Logger;

import javax.ejb.Stateless;
import javax.persistence.Query;
import java.io.Serializable;

/**
 * Created by Business_Book on 04.04.2016.
 */

@Stateless
public class AuthentificationManager extends AbstractManager implements Serializable {

    private static final Logger logger = Logger.getLogger(AuthentificationManager.class);

    public AuthentificationManager() {
    }

    public User getUserByEmailPassword(String email, String password) {
        Query query = manager.createQuery("select u from User u where u.email = :email and u.password = :password ");
        query.setParameter("email", email);
        query.setParameter("password", password);
        return getSafeSingleResult(query);
    }
}
