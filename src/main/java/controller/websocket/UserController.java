package controller.websocket;

import model.entity.Token;
import model.entity.User;
import model.enums.MessageTypeEnum;
import model.message.Message;
import model.request.AuthRequestEntity;
import model.response.AuthResponseEntity;
import model.response.ErrorResponseEntity;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.websocket.dao.AuthentificationManager;
import org.websocket.dao.UserManager;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

import static java.util.UUID.randomUUID;

/**
 * Created by Business_Book on 08.04.2016.
 */

@Stateless
public class UserController {

    private static final Logger logger = Logger.getLogger(UserController.class);

    @Inject
    AuthentificationManager authentificationManager;

    @Inject
    UserManager userManager;

    public User createNewUser() throws Exception {
        User user = new User();
        user.setLogin("test");
        user.setPassword(saltPassword("test"));
        user.getTokens().add(createNewToken());
        user = userManager.save(user);
        return user;
    }

    private String saltPassword(String password){
        StringBuilder sb = new StringBuilder();
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.reset();
            md.update(password.getBytes(Charset.forName("UTF8")));
            byte byteData[] = md.digest();
            for (byte aByteData : byteData) {
                sb.append(Integer.toString((aByteData & 0xff) + 0x100, 16).substring(1));
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    public Message authUser(String msg) throws Exception {
        Message message = getMessage(msg);
        AuthRequestEntity requestEntity = (AuthRequestEntity) message.getData();
        User user = authentificationManager.getUserByLoginPassword(requestEntity.getLogin(), requestEntity.getPassword());
        if (user != null) {
            Token token = createNewToken();
            user.getTokens().add(token);
            authentificationManager.update(user);
            message.setType(MessageTypeEnum.CUSTOMER_API_TOKEN);
            message.setData(createResponseData(token));
        } else {
            message.setType(MessageTypeEnum.CUSTOMER_ERROR);
            message.setData(createResponseError());
        }
        return message;
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
        message.setSequenceId(randomUUID().toString());
        try {
            AuthRequestEntity requestEntity = mapper.readValue(msg, AuthRequestEntity.class);
            message.setData(requestEntity);
        } catch (Exception e) {
            message.setData(null);
        }
        return message;
    }

    private Token createNewToken() throws Exception {
        Properties properties;
        try {
            properties = getProperties();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        Token token = new Token();
        token.setActive(true);
        token.setToken_guid(randomUUID().toString());
        String daysToExpire = properties.getProperty("daysToExpire");
        if (StringUtils.isNumeric(daysToExpire)) {
            token.setExpiration_date(createDateOfExpire(new Integer(daysToExpire)));
        } else {
            throw new Exception("Wrong days number");
        }
        return token;
    }

    private Date createDateOfExpire(int daysFromNow) {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.DATE, daysFromNow);
        return c.getTime();
    }

    private Properties getProperties() throws Exception {
        Properties properties = new Properties();
        InputStream input = null;
        String propertyFileName = "config.properties";
        try {
            input = this.getClass().getClassLoader().getResourceAsStream(propertyFileName);
            properties.load(input);
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                throw new Exception("File Not Found");
            }
        }
        return properties;
    }
}
