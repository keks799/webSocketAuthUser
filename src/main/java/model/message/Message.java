package model.message;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeId;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import model.enums.MessageTypeEnum;
import model.interfaces.Data;
import model.request.AuthRequest;
import model.response.AuthResponse;
import model.response.ErrorResponse;
import org.apache.log4j.Logger;

/**
 * Created by Business_Book on 03.04.2016.
 */
public class Message {

    private static final Logger logger = Logger.getLogger(Message.class);

    @JsonProperty("type")
    @JsonTypeId
    private MessageTypeEnum messageType;

    @JsonProperty("sequence_id")
    private String sequenceid;

    @JsonProperty("data")
    private Data data;

    public Data getData() {
        return data;
    }

    @JsonTypeInfo(use=JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXTERNAL_PROPERTY, property="type")
    @JsonSubTypes(value = {
            @JsonSubTypes.Type(value=AuthRequest.class, name="LOGIN_CUSTOMER"),

            @JsonSubTypes.Type(value=AuthResponse.class, name="CUSTOMER_API_TOKEN"),

            @JsonSubTypes.Type(value=ErrorResponse.class, name="CUSTOMER_ERROR")
    })
    public void setData(Data data) {
        this.data = data;
    }

    public String getSequenceid() {
        return sequenceid;
    }

    public void setSequenceid(String sequenceid) {
        this.sequenceid = sequenceid;
    }

    public MessageTypeEnum getMessageType() {
        return messageType;
    }

    public void setMessageType(MessageTypeEnum messageType) {
        this.messageType = messageType;
    }
}
