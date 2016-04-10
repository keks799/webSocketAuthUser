package org.websocket.client;

import model.enums.MessageTypeEnum;
import model.message.Message;
import model.request.AuthRequest;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import java.net.URI;
import java.net.URISyntaxException;

import static utils.JsonUtils.objectToJson;

/**
 * Created by Business_Book on 10.04.2016.
 */
public class Main {

    private static final Logger logger = Logger.getLogger(Main.class);

    public static void main(String[] args) {
        String address = "";
        String messageString = "";

        if(args.length > 0) {
            address = args[0];
            messageString = args[1];
        }

        if(StringUtils.isEmpty(address)){
            address = "ws://localhost:8080/websocket";
        }

        if(StringUtils.isEmpty(messageString)) {
            Message message = new Message(MessageTypeEnum.LOGIN_CUSTOMER, new AuthRequest("qwe@qwe.com", "123"));
            messageString = objectToJson(message);
        }
        try {
            final MyWebsocketClientEndpoint myWebsocketClientEndPoint = new MyWebsocketClientEndpoint(new URI(address));

            myWebsocketClientEndPoint.addMessageHandler(new MyWebsocketClientEndpoint.MessageHandler() {
                public void handleMessage(String message) {
                    logger.info(message);
                }
            });

            sendMessage(messageString, myWebsocketClientEndPoint);

            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    private static void sendMessage(String message, MyWebsocketClientEndpoint myWebsocketClientEndPoint) {
        myWebsocketClientEndPoint.sendMessage(message);
    }
}
