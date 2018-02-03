package org.chaos.cp.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private String login;

    public User(String login) {
        this.login = login;
    }

    @Override
    public String toString() {
        return String.format(
                "Customer[id=%d, login='%s']",
                id, login);
    }

    public Long getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

}
