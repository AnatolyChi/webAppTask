package com.example.webAppTask.bean;

import lombok.*;

import java.io.Serializable;

@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
public final class RegistrationInfo implements Serializable {
    private String login;
    private String password;

    public RegistrationInfo(String login, String password) {
        this.login = login;
        this.password = password;
    }
}
