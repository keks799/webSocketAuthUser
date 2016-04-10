package org.websocket.dao;

import model.entity.User;
import org.apache.log4j.Logger;

import javax.ejb.Stateless;
import javax.persistence.Query;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Business_Book on 08.04.2016.
 */

@Stateless
public class UserManager extends AbstractManager implements Serializable {

    private static final Logger logger = Logger.getLogger(UserManager.class);

    public UserManager() {
    }

    public User getUserById(Long id) {
        return manager.find(User.class, id);
    }

    public List<User> getUsersByEmail(String email) {
        Query query = manager.createQuery("select u from User u where u.email like :email");
        query.setParameter("email", normalizeStringForQuery(email));
        return getSafeResultList(query);
    }
}
