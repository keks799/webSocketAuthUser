package model.entity;

import org.apache.log4j.Logger;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by Business_Book on 06.04.2016.
 */

@Entity
@Table(name = "user")
public class User {

    private static final Logger logger = Logger.getLogger(User.class);

    public User() {
    }

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "login")
    private String login;

    @Column(name = "password")
    private String password;

    @OneToMany
    @JoinTable(
            name = "user_token",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "token_id")
    )
    @Cascade(CascadeType.ALL)
    private Set<Token> tokens;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public Set<Token> getTokens() {
        if(tokens == null) {
            tokens = new LinkedHashSet<Token>();
        }
        return tokens;
    }

    public void setTokens(TreeSet<Token> tokens) {
        this.tokens = tokens;
    }
}
