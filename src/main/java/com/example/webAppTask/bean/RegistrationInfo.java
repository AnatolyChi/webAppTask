package com.example.webAppTask.bean;

import jakarta.servlet.http.HttpServletRequest;
import lombok.*;

import java.io.Serializable;

@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
public final class RegistrationInfo implements Serializable {

    private static final String LOGIN = "login";
    private static final String PASSWORD = "password";

    private String login;
    private String password;

    public RegistrationInfo(HttpServletRequest request) {
        this.login = request.getParameter(LOGIN);
        this.password = request.getParameter(PASSWORD);
    }
}
