package com.example.webapptask.controller.validator;

import com.example.webapptask.bean.RegistrationInfo;
import com.example.webapptask.bean.UpdateUserInfo;

public interface UserValidator {
    boolean validParamInfo(RegistrationInfo info);
    boolean validUpdateInfo(UpdateUserInfo info);
}
