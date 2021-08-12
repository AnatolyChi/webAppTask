package com.example.webAppTask.bean;

import lombok.*;

import java.io.Serializable;
import java.util.Date;

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
    private String age;
    @Setter
    private String email;
    private String role;
    private Date dateRegistered;

    public User(String login, Date dateRegistered) {
        this.login = login;
        this.dateRegistered = dateRegistered;
    }


}
