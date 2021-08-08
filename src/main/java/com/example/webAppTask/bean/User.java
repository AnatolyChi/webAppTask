package com.example.webAppTask.bean;

import lombok.*;

import java.io.Serializable;
import java.sql.Date;

@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
public final class User implements Serializable {
    private String login;
    @Setter
    private String firstName;
    @Setter
    private String lastName;
    @Setter
    private String email;
    private Date dateRegistered;

    public User(String login) {
        this.login = login;
    }

    public User(String login, Date dateRegistered) {
        this.login = login;
        this.dateRegistered = dateRegistered;
    }

    public User(String login, String firstName, String lastName, String email) {
        this.login = login;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }
}
