package model.message;

import model.enums.MessageTypeEnum;
import model.interfaces.Data;
import model.request.AuthRequestEntity;
import model.response.AuthResponseEntity;
import model.response.ErrorResponseEntity;
import org.apache.log4j.Logger;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonSubTypes;
import org.codehaus.jackson.annotate.JsonTypeInfo;

/**
 * Created by Business_Book on 03.04.2016.
 */
public class Message {

    private static final Logger logger = Logger.getLogger(Message.class);

    @JsonProperty("type")
    private MessageTypeEnum type;

    @JsonProperty("sequence_id")
    private String sequenceid;

    @JsonProperty("data")
    private Data data;

    public Data getData() {
        return data;
    }

    @JsonTypeInfo(use=JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXTERNAL_PROPERTY, property="type")
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

    public MessageTypeEnum getType() {
        return type;
    }

    public void setType(MessageTypeEnum type) {
        this.type = type;
    }
}
