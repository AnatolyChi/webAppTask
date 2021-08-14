package com.example.webapptask.dao;

import com.example.webapptask.bean.RegistrationInfo;
import com.example.webapptask.bean.User;
import com.example.webapptask.dao.exception.DAOException;

import java.util.Optional;

public interface UserDAO {
    Optional<User> get(RegistrationInfo info) throws DAOException;
    void add(RegistrationInfo info) throws DAOException;
    void update(User user, String firstName, String lastName, String email, String age) throws DAOException;
}
