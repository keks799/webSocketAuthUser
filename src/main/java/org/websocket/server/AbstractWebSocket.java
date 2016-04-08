package org.websocket.server;

import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;

import javax.websocket.*;
import java.io.IOException;

/**
 * Created by Business_Book on 08.04.2016.
 */

public abstract class AbstractWebSocket {

    private static final Logger logger = Logger.getLogger(AbstractWebSocket.class);

    @OnOpen
    public void onConnect(Session session) throws IOException {
        logger.info("connected!");
    }

    @OnMessage
    public abstract String onText(String msg);

    @OnClose
    public void onClose(CloseReason reason) {
        logger.info(reason.getCloseCode().getCode());
    }

    public String entityToJson(Object o) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(o);
        } catch (IOException e) {
            e.printStackTrace();
            return e.getLocalizedMessage();
        }
    }
}
