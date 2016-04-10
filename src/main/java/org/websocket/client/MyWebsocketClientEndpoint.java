package org.websocket.client;

import org.apache.log4j.Logger;

import javax.websocket.*;
import java.net.URI;

/**
 * Created by Business_Book on 10.04.2016.
 */
@ClientEndpoint
public class MyWebsocketClientEndpoint {

    private MessageHandler messageHandler;
    private Session session;

    private static final Logger logger = Logger.getLogger(MyWebsocketClientEndpoint.class);

    public MyWebsocketClientEndpoint(URI endpointURI) {
        try {
            WebSocketContainer container = ContainerProvider.getWebSocketContainer();
            container.connectToServer(MyWebsocketClientEndpoint.class, endpointURI);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @OnOpen
    public void onOpen(Session session) {
        this.session = session;
        logger.info("Connected to endpoint: " + session.getBasicRemote());
    }

    @OnMessage
    public void gotMessage(String message) {
        if (this.messageHandler != null) {
            this.messageHandler.handleMessage(message);
        }
        logger.info("Received message on client: " + message);
    }

    @OnError
    public void gotError(Throwable t) {
        t.printStackTrace();
    }

    @OnClose
    public void onClose(Session session, CloseReason reason) {
        System.out.println("closing websocket with reason " + reason + " in session " + session.getId());
        this.session = null;
    }

    public void addMessageHandler(MessageHandler msgHandler) {
        this.messageHandler = msgHandler;
    }

    public void sendMessage(String message) {
        this.session.getAsyncRemote().sendText(message);
    }

    public interface MessageHandler {
        void handleMessage(String message);
    }
}
