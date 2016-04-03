package model.request;

import org.apache.log4j.Logger;

/**
 * Created by Business_Book on 03.04.2016.
 */
public class AuthRequestEntity {

    private static final Logger logger = Logger.getLogger(AuthRequestEntity.class);

    private String email;

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
