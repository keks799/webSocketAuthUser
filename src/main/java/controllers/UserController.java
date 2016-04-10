package controllers;

import model.entity.User;
import model.message.Message;
import org.apache.log4j.Logger;
import services.UserService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.websocket.Session;

import static utils.JsonUtils.objectToJson;

/**
 * Created by Business_Book on 10.04.2016.
 */

@Stateless
public class UserController {

    private static final Logger logger = Logger.getLogger(UserController.class);

    @Inject
    UserService userService;

    public String createNewUser(Session session, String msg) {
        logger.info("create new user in session: " + session.getId());
        User user;
        try {
            user = userService.createNewUser(msg);
            return objectToJson(user);
        } catch (Exception e) {
            e.printStackTrace();
            return e.getLocalizedMessage();
        }
    }

    public String authUser(Session session, Message message) {
        logger.info("trying to authenticate user in session: " + session.getId());
        Message response;
        try {
            response = userService.authUser(message);
            return objectToJson(response);
        } catch (Exception e) {
            e.printStackTrace();
            return e.getLocalizedMessage();
        }
    }
}
