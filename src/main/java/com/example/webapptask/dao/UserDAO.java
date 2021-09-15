package com.example.webapptask.dao;

import com.example.webapptask.bean.RegistrationInfo;
import com.example.webapptask.bean.UpdateUserInfo;
import com.example.webapptask.bean.User;
import com.example.webapptask.dao.exception.DAOException;

import java.util.Optional;

public interface UserDAO {

    Optional<User> get(RegistrationInfo info) throws DAOException;
    boolean add(RegistrationInfo info) throws DAOException;
    void update(UpdateUserInfo info) throws DAOException;
}
