package com.example.webapptask.controller.validator;

import com.example.webapptask.bean.RegistrationInfo;
import com.example.webapptask.bean.UpdateUserInfo;

public class UserValidatorImpl implements UserValidator {

    private static final UserValidator USER_VALIDATOR = new UserValidatorImpl();

//    private static final String LOGIN_CHECK = "^[A-Za-z]([.A-Za-z0-9-]{1,18})([A-Za-z0-9])$";
//    private static final String PASSWORD_CHECK = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]){5,}";
    private static final String LOGIN_CHECK = "^[A-Za-z]([.A-Za-z0-9-]{1,10})([A-Za-z0-9])$";
    private static final String PASSWORD_CHECK = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{5,}";
    private static final String FIRSTNAME_AND_LASTNAME_CHECK = "^([А-Я]{1}[а-яё]{1,23}|[A-Z]{1}[a-z]{1,23})$";
    private static final String EMAIL_CHECK = "^[^@]+@[^@.]+\\.[^@]+$";
    private static final String AGE_CHECK = "^(?:1(?:00?|\\d)|[2-5]\\d|[6-9]\\d?)$";

    private UserValidatorImpl() {

    }

    public static UserValidator getValidator() {
        return USER_VALIDATOR;
    }

    @Override
    public boolean validParamInfo(RegistrationInfo info) {
        return !info.getLogin().matches(LOGIN_CHECK) ||
                !info.getPassword().matches(PASSWORD_CHECK);
    }

    @Override
    public boolean validUpdateInfo(UpdateUserInfo info) {
        return  !info.getFirstname().matches(FIRSTNAME_AND_LASTNAME_CHECK) &&
                !info.getLastname().matches(FIRSTNAME_AND_LASTNAME_CHECK) &&
                !info.getEmail().matches(EMAIL_CHECK) &&
                !String.valueOf(info.getAge()).matches(AGE_CHECK);
    }
}
