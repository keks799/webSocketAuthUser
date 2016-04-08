package org.websocket.server;

import model.entity.Token;
import model.enums.MessageTypeEnum;
import model.message.Message;
import model.request.AuthRequestEntity;
import model.response.AuthResponseEntity;
import model.response.ErrorResponseEntity;
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.websocket.dao.AuthentificationManager;

import javax.inject.Inject;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.UUID;

@ServerEndpoint("/auth")
public class UserAuthWebSocket {

    private static final Logger logger = Logger.getLogger(UserAuthWebSocket.class);

    @Inject
    AuthentificationManager authentificationManager;

    @OnMessage
    public String onText(String msg) throws IOException {
        logger.info("Message received:\n" + msg);
        Message message = getMessage(msg);

        AuthRequestEntity requestEntity = (AuthRequestEntity) message.getData();
        Token token = authentificationManager.getUserTokenByLoginPassword(requestEntity.getLogin(), requestEntity.getPassword());
        if (token != null) {
            message.setType(MessageTypeEnum.CUSTOMER_API_TOKEN);
            message.setData(createResponseData(token));
        } else {
            message.setType(MessageTypeEnum.CUSTOMER_ERROR);
            message.setData(createResponseError());
        }
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(message);
    }

    private ErrorResponseEntity createResponseError() {
        ErrorResponseEntity responseEntity = new ErrorResponseEntity();
        responseEntity.setErrorDescription("Customer not found");
        responseEntity.setErrorCode("customer.notFound");
        return responseEntity;
    }

    private AuthResponseEntity createResponseData(Token token) {
        AuthResponseEntity responseEntity = new AuthResponseEntity();
        responseEntity.setApiToken(token.getToken_guid());
        responseEntity.setApiTokenExpirationDate(token.getExpiration_date());
        return responseEntity;
    }

    private Message getMessage(String msg) {
        ObjectMapper mapper = new ObjectMapper();
        Message message = new Message();
        message.setType(MessageTypeEnum.LOGIN_CUSTOMER);
        message.setSequenceId(UUID.randomUUID().toString());
        try {
            AuthRequestEntity requestEntity = mapper.readValue(msg, AuthRequestEntity.class);
            message.setData(requestEntity);
        } catch (Exception e) {
            message.setData(null);
        }
        return message;
    }

    @OnOpen
    public void onConnect(Session session) throws IOException {
        logger.info("connected!");
    }

    @OnClose
    public void onClose(CloseReason reason) {
        logger.info(reason.getCloseCode().getCode());
    }

}
