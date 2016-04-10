package controllers;

import model.entity.User;
import model.message.Message;
import org.apache.log4j.Logger;
import services.UserService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.websocket.Session;

import static utils.JsonUtils.entityToJson;

/**
 * Created by Business_Book on 10.04.2016.
 */

@Stateless
public class UserController {

    private static final Logger logger = Logger.getLogger(UserController.class);

    @Inject
    UserService userService;

    public String createNewUser(Session session, String msg) {
        logger.info("in session: " + session.getId() + " got message\n" + msg);
        User user;
        try {
            user = userService.createNewUser(msg);
            return entityToJson(user);
        } catch (Exception e) {
            e.printStackTrace();
            return e.getLocalizedMessage();
        }
    }

    public String authUser(Session session, Message message) {
        logger.info("in session: " + session.getId() + " got message\n" + message);
        Message response;
        try {
            response = userService.authUser(message);
            return entityToJson(response);
        } catch (Exception e) {
            e.printStackTrace();
            return e.getLocalizedMessage();
        }
    }
}
