package com.example.webAppTask.service;

import com.example.webAppTask.bean.RegistrationInfo;
import com.example.webAppTask.bean.User;
import com.example.webAppTask.service.exception.ServiceException;

import java.util.Optional;

public interface UserService {
    Optional<User> registration(RegistrationInfo info) throws ServiceException;
    Optional<User> authorization(RegistrationInfo info) throws ServiceException;
    void personalUpdate(User user, String firstName, String lastName, String email, String age) throws ServiceException;
}
