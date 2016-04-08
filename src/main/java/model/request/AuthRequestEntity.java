package model.request;

import org.apache.log4j.Logger;

/**
 * Created by Business_Book on 03.04.2016.
 */
public class AuthRequestEntity {

    private static final Logger logger = Logger.getLogger(AuthRequestEntity.class);

    private String login;

    private String password;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
