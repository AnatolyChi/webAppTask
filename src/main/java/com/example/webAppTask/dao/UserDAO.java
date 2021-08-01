package com.example.webAppTask.dao;

import com.example.webAppTask.bean.RegistrationInfo;
import com.example.webAppTask.bean.User;
import com.example.webAppTask.dao.exception.DAOException;

import java.util.Optional;

public interface UserDAO {
    Optional<User> get(RegistrationInfo info) throws DAOException;
    void add(RegistrationInfo info) throws DAOException;
    void update(User user) throws DAOException;
}
