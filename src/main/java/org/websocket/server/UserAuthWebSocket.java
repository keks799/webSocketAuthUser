package org.websocket.server;

import controller.websocket.UserController;
import model.message.Message;
import org.apache.log4j.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.websocket.OnMessage;
import javax.websocket.server.ServerEndpoint;

import static utils.JsonUtilities.entityToJson;

@Stateless
@ServerEndpoint("/auth")
public class UserAuthWebSocket extends AbstractWebSocket {

    private static final Logger logger = Logger.getLogger(UserAuthWebSocket.class);

    @Inject
    UserController userController;

    @OnMessage
    public String onText(String msg) {
        logger.info("Message received:\n" + msg);
        Message response;
        try {
            response = userController.authUser(msg);
            return entityToJson(response);
        } catch (Exception e) {
            e.printStackTrace();
            return e.getLocalizedMessage();
        }
    }

}
