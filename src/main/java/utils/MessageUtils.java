package utils;

import model.message.Message;
import org.apache.log4j.Logger;

import static utils.JsonUtils.jsonToEntity;

/**
 * Created by Business_Book on 10.04.2016.
 */
public class MessageUtils {

    private static final Logger logger = Logger.getLogger(MessageUtils.class);

    public static Message getMessage(String msg) {
        Message message = new Message();
        try {
            message = jsonToEntity(msg, Message.class);
        } catch (Exception e) {
            message.setData(null);
        }
        return message;
    }
}
