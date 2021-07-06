package com.example.webAppTask.service;

import com.example.webAppTask.bean.User;

import java.util.List;

public interface UserService {
    void registration(User user) throws ServiceException;
    void authorization(User user) throws ServiceException;
    List<String> listAllUsers() throws ServiceException;
    boolean search(User user) throws ServiceException;
}
