package model.response;

import model.interfaces.Data;
import org.apache.log4j.Logger;
import org.codehaus.jackson.annotate.JsonProperty;

import java.util.Date;

/**
 * Created by Business_Book on 03.04.2016.
 */
public class AuthResponseEntity implements Data {

    private static final Logger logger = Logger.getLogger(AuthResponseEntity.class);

    @JsonProperty("apt_token")
    private String apiToken;

    @JsonProperty("api_token_expiration_date")
    private Date apiTokenExpirationDate;

    public String getApiToken() {
        return apiToken;
    }

    public void setApiToken(String apiToken) {
        this.apiToken = apiToken;
    }

    public Date getApiTokenExpirationDate() {
        return apiTokenExpirationDate;
    }

    public void setApiTokenExpirationDate(Date apiTokenExpirationDate) {
        this.apiTokenExpirationDate = apiTokenExpirationDate;
    }
}
