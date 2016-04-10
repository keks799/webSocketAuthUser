package org.websocket.server;

import controller.websocket.AuditController;
import org.apache.log4j.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

/**
 * Created by Business_Book on 10.04.2016.
 */

@Stateless
@ServerEndpoint("/audit")
public class AuditWebSocket extends AbstractWebSocket {

    @Inject
    AuditController auditController;

    private static final Logger logger = Logger.getLogger(AuditWebSocket.class);

    @Override
    public String onMessage(Session session, String msg) {
        logger.info("in session: " + session.getId() + " received message\n" + msg);
        auditController.
        return null;
    }
}
