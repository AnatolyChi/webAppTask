package com.example.webAppTask.bean;

import lombok.Data;

import java.io.Serializable;

@Data
public class User implements Serializable {
    private String login;
    private String firstName;
    private String lastName;
    private String password;

    public User() {
    }

    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }
}
