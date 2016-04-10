package org.websocket.server;

import controllers.UserController;
import model.message.Message;
import org.apache.log4j.Logger;

import javax.ejb.Stateful;
import javax.inject.Inject;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static utils.MessageUtils.getMessage;

/**
 * Created by Business_Book on 08.04.2016.
 */

@Stateful
@ServerEndpoint("/websocket")
public class WebSocket {

    @Inject
    UserController userController;

    private static final Logger logger = Logger.getLogger(WebSocket.class);

    private static final Set<Session> sessions = Collections.synchronizedSet(new HashSet<Session>());

    @OnOpen
    public void onConnect(Session session) throws IOException {
        sessions.add(session);
        logger.info("connected! session is: " + session.getId());
    }

    @OnMessage
    public String onMessage(Session session, String msg) {
        logger.info("in session: " + session.getId() + " got message\n" + msg);
        Message message = getMessage(msg);
        switch (message.getMessageType()) {
            case LOGIN_CUSTOMER : {
                return userController.authUser(session, message);
            }
            case SIGNUP_CUSTOMER : {
                return userController.createNewUser(session, msg);
            }
            default: {
                return "Unknown message type";
            }
        }
    }

    @OnClose
    public void onClose(CloseReason reason, Session session) {
        sessions.remove(session);
        logger.info("closed session " + reason.getCloseCode().getCode());
    }
}
