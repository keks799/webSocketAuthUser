package model.response;

import org.apache.log4j.Logger;

import java.util.Date;

/**
 * Created by Business_Book on 03.04.2016.
 */
public class AuthResponseEntity {

    private static final Logger logger = Logger.getLogger(AuthResponseEntity.class);

    private String apiToken;

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
