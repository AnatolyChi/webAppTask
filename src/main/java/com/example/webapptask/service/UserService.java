package com.example.webapptask.service;

import com.example.webapptask.bean.RegistrationInfo;
import com.example.webapptask.bean.User;
import com.example.webapptask.service.exception.ServiceException;

import java.util.Optional;

public interface UserService {
    Optional<User> registration(RegistrationInfo info) throws ServiceException;
    Optional<User> authorization(RegistrationInfo info) throws ServiceException;
    void personalUpdate(User user, String firstName, String lastName, String email, String age) throws ServiceException;
}
