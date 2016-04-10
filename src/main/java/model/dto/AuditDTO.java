package model.dto;

import org.apache.log4j.Logger;

import java.util.Date;

/**
 * Created by Business_Book on 10.04.2016.
 */
public class AuditDTO {

    private static final Logger logger = Logger.getLogger(AuditDTO.class);

    private String email;
    private String token;
    private Date expireDate;
    private boolean active;
    private Date dateOfIssue;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Date getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Date getDateOfIssue() {
        return dateOfIssue;
    }

    public void setDateOfIssue(Date dateOfIssue) {
        this.dateOfIssue = dateOfIssue;
    }
}
