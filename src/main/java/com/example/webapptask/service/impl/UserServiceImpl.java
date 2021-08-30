package com.example.webapptask.service.impl;

import com.example.webapptask.bean.RegistrationInfo;
import com.example.webapptask.bean.UpdateUserInfo;
import com.example.webapptask.bean.User;
import com.example.webapptask.controller.validator.UserValidatorImpl;
import com.example.webapptask.dao.exception.DAOException;
import com.example.webapptask.dao.DAOProvider;
import com.example.webapptask.dao.UserDAO;
import com.example.webapptask.controller.validator.UserValidator;
import com.example.webapptask.service.exception.ServiceException;
import com.example.webapptask.service.UserService;
import lombok.extern.log4j.Log4j2;

import java.util.Optional;

@Log4j2
public class UserServiceImpl implements UserService {

    private static final DAOProvider PROVIDER = DAOProvider.getInstance();
    private static final UserDAO USER_DAO = PROVIDER.getUSER_DAO();

    @Override
    public boolean registration(RegistrationInfo info) throws ServiceException {

        try {
            return USER_DAO.add(info);
        } catch (DAOException e) {
            log.error("error on registration", e);
            throw new ServiceException(e);
        }
    }

    @Override
    public Optional<User> authorization(RegistrationInfo info) throws ServiceException {

        Optional<User> user;
        try {
            user = USER_DAO.get(info);
        } catch (DAOException e) {
            log.error("error on authorization", e);
            throw new ServiceException(e);
        }

        return user;
    }

    @Override
    public void personalUpdate(UpdateUserInfo info) throws ServiceException {

        try {
            USER_DAO.update(info);
        } catch (DAOException e) {
            log.error("error on updating", e);
            throw new ServiceException(e);
        }
    }
}
