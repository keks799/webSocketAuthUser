package model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import model.interfaces.Data;
import org.apache.log4j.Logger;

/**
 * Created by Business_Book on 03.04.2016.
 */
public class AuthRequest implements Data {

    private static final Logger logger = Logger.getLogger(AuthRequest.class);

    public AuthRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }

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
