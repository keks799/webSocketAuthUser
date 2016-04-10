package model.entity;

import model.Comparator.TokenComparator;
import org.apache.log4j.Logger;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.SortComparator;

import javax.persistence.*;
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

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "login")
    private String email;

    @Column(name = "password")
    private String password;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "token_audit",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "token_id")
    )
    @Cascade(CascadeType.ALL)
    @SortComparator(TokenComparator.class)
    private Set<Token> tokens = new TreeSet<Token>(new TokenComparator());

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public Set<Token> getTokens() {
        return tokens;
    }

    public void setTokens(TreeSet<Token> tokens) {
        this.tokens = tokens;
    }
}
