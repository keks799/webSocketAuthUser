package model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import model.interfaces.Data;
import org.apache.log4j.Logger;

/**
 * Created by Business_Book on 03.04.2016.
 */
public class AuthResponseEntity implements Data {

    private static final Logger logger = Logger.getLogger(AuthResponseEntity.class);

    @JsonProperty("apt_token")
    private String apiToken;

    @JsonProperty("api_token_expiration_date")
    private String apiTokenExpirationDate;

    public String getApiToken() {
        return apiToken;
    }

    public void setApiToken(String apiToken) {
        this.apiToken = apiToken;
    }

    public String getApiTokenExpirationDate() {
        return apiTokenExpirationDate;
    }

    public void setApiTokenExpirationDate(String apiTokenExpirationDate) {
        this.apiTokenExpirationDate = apiTokenExpirationDate;
    }
}
