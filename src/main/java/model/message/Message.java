package model.message;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import model.enums.MessageTypeEnum;
import model.interfaces.Data;
import model.request.AuthRequestEntity;
import model.response.AuthResponseEntity;
import model.response.ErrorResponseEntity;
import org.apache.log4j.Logger;

/*import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonSubTypes;
import org.codehaus.jackson.annotate.JsonTypeInfo;*/

/**
 * Created by Business_Book on 03.04.2016.
 */
public class Message {

    private static final Logger logger = Logger.getLogger(Message.class);

    @JsonProperty("type")
    private MessageTypeEnum messageType;

    @JsonProperty("sequence_id")
    private String sequenceid;

    @JsonProperty("data")
    private Data data;

    public Data getData() {
        return data;
    }

    @JsonTypeInfo(use=JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXTERNAL_PROPERTY, property="type") // todo!!! important!!!
    @JsonSubTypes(value = {
            @JsonSubTypes.Type(value=AuthRequestEntity.class, name="LOGIN_CUSTOMER"),

            @JsonSubTypes.Type(value=AuthResponseEntity.class, name="CUSTOMER_API_TOKEN"),

            @JsonSubTypes.Type(value=ErrorResponseEntity.class, name="CUSTOMER_ERROR")
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
