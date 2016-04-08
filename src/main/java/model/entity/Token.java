package model.entity;

import org.apache.log4j.Logger;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Business_Book on 04.04.2016.
 */

@Entity
@Table(name = "token")
public class Token implements Serializable {

    private static final Logger logger = Logger.getLogger(Token.class);

    public Token() {

    }

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "token_guid")
    private String token_guid;

    @Column(name = "expiration_date")
    private Date expiration_date;

    @Column(name = "active")
    private boolean active;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getToken_guid() {
        return token_guid;
    }

    public void setToken_guid(String token_guid) {
        this.token_guid = token_guid;
    }

    public Date getExpiration_date() {
        return expiration_date;
    }

    public void setExpiration_date(Date expiration_date) {
        this.expiration_date = expiration_date;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
