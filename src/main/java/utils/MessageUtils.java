package utils;

import model.message.Message;
import org.apache.log4j.Logger;

import static utils.JsonUtils.jsonToObject;

/**
 * Created by Business_Book on 10.04.2016.
 */
public class MessageUtils {

    private static final Logger logger = Logger.getLogger(MessageUtils.class);

    public static Message getMessage(String msg) throws Exception {
        Message message;
        try {
            message = jsonToObject(msg, Message.class);
        } catch (Exception e) {
            throw new Exception("Wrong message error.");
        }
        return message;
    }
}
