package services;

import model.entity.Token;
import model.entity.User;
import model.enums.MessageTypeEnum;
import model.message.Message;
import model.request.AuthRequest;
import model.response.AuthResponse;
import model.response.ErrorResponse;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.validator.routines.EmailValidator;
import org.apache.log4j.Logger;
import org.websocket.dao.AuthentificationManager;
import org.websocket.dao.UserManager;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

import static java.util.UUID.randomUUID;
import static utils.DateUtils.convertToRFC3339;
import static utils.JsonUtils.jsonToEntity;

/**
 * Created by Business_Book on 08.04.2016.
 */

@Stateless
public class UserService {

    private static final Logger logger = Logger.getLogger(UserService.class);

    @Inject
    AuthentificationManager authentificationManager;

    @Inject
    UserManager userManager;

    public User createNewUser(String message) throws Exception {
        User user = jsonToEntity(message, User.class);
        user.getTokens().add(createNewToken());
        user = userManager.save(user);
        return user;
    }

    public Message authUser(Message message) throws Exception {
        AuthRequest request = (AuthRequest) message.getData();

        String email = request.getEmail().trim();
        String password = request.getPassword().trim();
        if(EmailValidator.getInstance().isValid(email) && email.length() > 0 && password.length() > 0 && email.length() < 255 && password.length() <= 255) {
            User user = authentificationManager.getUserByEmailPassword(email, password);
            if (user != null) {
                Token token = createNewToken();
                ((Token)((SortedSet) user.getTokens()).first()).setActive(false);
                user.getTokens().add(token);
                authentificationManager.update(user);
                message.setMessageType(MessageTypeEnum.CUSTOMER_API_TOKEN);
                message.setData(createResponseData(token));
            } else {
                message.setMessageType(MessageTypeEnum.CUSTOMER_ERROR);
                message.setData(createResponseError());
            }
        } else {
            throw new Exception("Wrong email");
        }
        return message;
    }

    private ErrorResponse createResponseError() {
        ErrorResponse responseEntity = new ErrorResponse();
        responseEntity.setErrorDescription("Customer not found");
        responseEntity.setErrorCode("customer.notFound");
        return responseEntity;
    }

    private AuthResponse createResponseData(Token token) {
        AuthResponse responseEntity = new AuthResponse();
        responseEntity.setApiToken(token.getToken_guid());
        responseEntity.setApiTokenExpirationDate(convertToRFC3339(token.getExpiration_date()));
        return responseEntity;
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
        token.setCreationDate(new Date());
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

    public List<User> getUsers(String userIdentifier) {
        List<User> users = new ArrayList<User>();
        userIdentifier = userIdentifier.trim();
        if(StringUtils.isNotBlank(userIdentifier)) {
            if(StringUtils.isNumeric(userIdentifier)) {
                users.add(userManager.getUserById(new Long(userIdentifier)));
            } else {
                users.addAll(userManager.getUsersByEmail(userIdentifier));
            }
        }
        return users;
    }
}
