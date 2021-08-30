package com.example.webapptask.service;

import com.example.webapptask.bean.RegistrationInfo;
import com.example.webapptask.bean.UpdateUserInfo;
import com.example.webapptask.bean.User;
import com.example.webapptask.service.exception.ServiceException;

import java.util.Optional;

public interface UserService {
    boolean registration(RegistrationInfo info) throws ServiceException;
    Optional<User> authorization(RegistrationInfo info) throws ServiceException;
    void personalUpdate(UpdateUserInfo info) throws ServiceException;
}
