package model.request;

import model.interfaces.Data;
import org.apache.log4j.Logger;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 * Created by Business_Book on 03.04.2016.
 */
public class AuthRequestEntity implements Data {

    private static final Logger logger = Logger.getLogger(AuthRequestEntity.class);

    @JsonProperty("email")
    private String email;

    @JsonProperty("password")
    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
