package com.example.webapptask.service.impl;

import com.example.webapptask.bean.RegistrationInfo;
import com.example.webapptask.bean.User;
import com.example.webapptask.dao.exception.DAOException;
import com.example.webapptask.dao.DAOProvider;
import com.example.webapptask.dao.UserDAO;
import com.example.webapptask.service.exception.ServiceException;
import com.example.webapptask.service.UserService;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

@Slf4j
public class UserServiceImpl implements UserService {
    private static final DAOProvider PROVIDER = DAOProvider.getInstance();
    private static final UserDAO USER_DAO = PROVIDER.getUSER_DAO();
    private static final String LOG_ERR_MESSAGE = "Incorrect login or password";

    @Override
    public Optional<User> registration(RegistrationInfo info) throws ServiceException {
        if (validate(info)) {
            log.error(LOG_ERR_MESSAGE);
            throw new ServiceException();
        }

        Optional<User> user;
        try {
            user = USER_DAO.add(info);
        } catch (DAOException e) {
            e.printStackTrace();
            throw new ServiceException(e);
        }

        return user;
    }

    @Override
    public Optional<User> authorization(RegistrationInfo info) throws ServiceException {
        if (validate(info)) {
            log.error(LOG_ERR_MESSAGE);
            throw new ServiceException();
        }

        Optional<User> user;
        try {
            user = USER_DAO.get(info);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }

        return user;
    }

    @Override
    public void personalUpdate(User user, String firstName, String lastName, String email, String age) throws ServiceException {
        if ((firstName != null && !firstName.equals(user.getFirstName())) ||
                (lastName != null && !lastName.equals(user.getLastName())) ||
                (email != null && !email.equals(user.getEmail()))) {

            try {
                USER_DAO.update(user, firstName, lastName, email, age);
            } catch (DAOException e) {
                throw new ServiceException(e);
            }
        }
    }

    private boolean validate(RegistrationInfo info) {
        return  info.getLogin() == null ||
                info.getLogin().isEmpty() ||
                info.getPassword() == null ||
                info.getPassword().isEmpty();
    }
}
