package com.example.webAppTask.service.impl;

import com.example.webAppTask.bean.RegistrationInfo;
import com.example.webAppTask.bean.User;
import com.example.webAppTask.dao.exception.DAOException;
import com.example.webAppTask.dao.DAOProvider;
import com.example.webAppTask.dao.UserDAO;
import com.example.webAppTask.service.exception.ServiceException;
import com.example.webAppTask.service.UserService;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

@Slf4j
public class UserServiceImpl implements UserService {
    private static final DAOProvider PROVIDER = DAOProvider.getInstance();
    private static final UserDAO USER_DAO = PROVIDER.getUserDAO();
    private static final String ERR_MESSAGE = "Incorrect login or password";

    @Override
    public Optional<User> registration(RegistrationInfo info) throws ServiceException {
        validate(info);
        Optional<User> user;

        try {
            user = USER_DAO.get(info);
            if (!user.isPresent()) {
                USER_DAO.add(info);
            }
        } catch (DAOException e) {
            e.printStackTrace();
            throw new ServiceException(e);
        }

        return user;
    }

    @Override
    public Optional<User> authorization(RegistrationInfo info) throws ServiceException {
        validate(info);
        Optional<User> user;

        try {
            user = USER_DAO.get(info);
        } catch (DAOException e) {
            e.printStackTrace();
            throw new ServiceException(e);
        }

        return user;
    }

    private void validate(RegistrationInfo info) throws ServiceException {
        if (info.getLogin() == null ||
            info.getLogin().isEmpty() ||
            info.getPassword() == null ||
            info.getPassword().isEmpty()) {
            throw new ServiceException(ERR_MESSAGE);
        }
    }
}
