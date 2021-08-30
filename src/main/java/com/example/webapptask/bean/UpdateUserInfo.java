package com.example.webapptask.bean;

import jakarta.servlet.http.HttpServletRequest;
import lombok.*;

import java.io.Serializable;

@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
public final class UpdateUserInfo implements Serializable {

    private static final String USER_ID_PARAM = "user_id";
    private static final String FIRSTNAME_PARAM = "firstname";
    private static final String LASTNAME_PARAM = "lastname";
    private static final String EMAIL_PARAM = "email";
    private static final String AGE_PARAM = "age";

    private int userId;
    private String firstname;
    private String lastname;
    private String email;
    private int age;

    public UpdateUserInfo(HttpServletRequest request) {
        userId = Integer.parseInt(request.getParameter(USER_ID_PARAM));
        firstname = request.getParameter(FIRSTNAME_PARAM);
        lastname = request.getParameter(LASTNAME_PARAM);
        email = request.getParameter(EMAIL_PARAM);
        age = Integer.parseInt(request.getParameter(AGE_PARAM));
    }
}
