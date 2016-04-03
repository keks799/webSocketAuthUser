package model.message;

import model.enums.MessageTypeEnum;
import org.apache.log4j.Logger;

/**
 * Created by Business_Book on 03.04.2016.
 */
public class Message {

    private static final Logger logger = Logger.getLogger(Message.class);

    private MessageTypeEnum type;

    private String sequenceId;

    private Object data;

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getSequenceId() {
        return sequenceId;
    }

    public void setSequenceId(String sequenceId) {
        this.sequenceId = sequenceId;
    }

    public MessageTypeEnum getType() {
        return type;
    }

    public void setType(MessageTypeEnum type) {
        this.type = type;
    }
}
