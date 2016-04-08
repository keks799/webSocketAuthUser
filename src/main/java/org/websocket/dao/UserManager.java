package org.websocket.dao;

import org.apache.log4j.Logger;

import javax.ejb.Stateless;
import java.io.Serializable;

/**
 * Created by Business_Book on 08.04.2016.
 */

@Stateless
public class UserManager extends AbstractManager implements Serializable {

    private static final Logger logger = Logger.getLogger(UserManager.class);

    public UserManager() {
    }
}
