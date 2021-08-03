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
    private String password;
    @Setter
    private String firstName;
    @Setter
    private String lastName;
    @Setter
    private String email;
    private Date dateRegistered;

    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public User(String login, String password, Date dateRegistered) {
        this.login = login;
        this.password = password;
        this.dateRegistered = dateRegistered;
    }
}
