package org.websocket.server;

import controller.websocket.UserController;
import model.entity.User;
import org.apache.log4j.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.websocket.server.ServerEndpoint;

/**
 * Created by Business_Book on 08.04.2016.
 */

@Stateless
@ServerEndpoint("/create")
public class CreateUserWebSocket extends AbstractWebSocket {

    private static final Logger logger = Logger.getLogger(CreateUserWebSocket.class);

    @Inject
    UserController userController;

    @Override
    public String onText(String msg) {
        User user = null;
        try {
            user = userController.createNewUser(msg);
            return userController.entityToJson(user);
        } catch (Exception e) {
            e.printStackTrace();
            return e.getLocalizedMessage();
        }
    }
}
