package org.websocket.server;

import org.apache.log4j.Logger;

import javax.ejb.Stateful;
import javax.websocket.*;
import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Business_Book on 08.04.2016.
 */

@Stateful
public abstract class AbstractWebSocket {

    private static final Logger logger = Logger.getLogger(AbstractWebSocket.class);

    private static final Set<Session> sessions = Collections.synchronizedSet(new HashSet<Session>());

    @OnOpen
    public void onConnect(Session session) throws IOException {
        sessions.add(session);
        logger.info("connected! session is: " + session);
    }

    @OnMessage
    public abstract String onMessage(Session session, String msg);

    @OnClose
    public void onClose(CloseReason reason, Session session) {
        sessions.remove(session);
        logger.info("closed session " + reason.getCloseCode().getCode());
    }
}
