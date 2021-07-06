package com.example.webAppTask.service.impl;

import com.example.webAppTask.bean.User;
import com.example.webAppTask.dao.DAOException;
import com.example.webAppTask.dao.DAOProvider;
import com.example.webAppTask.dao.UserDAO;
import com.example.webAppTask.service.ServiceException;
import com.example.webAppTask.service.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {
    private static final DAOProvider provider = DAOProvider.getInstance();
    private static final UserDAO userDAO = provider.getUserDAO();

    @Override
    public void registration(User user) throws ServiceException {
        try {
            if (!userDAO.findUser(user)) {
                userDAO.add(user);
            } else {
                throw new DAOException();
            }
        } catch (DAOException e) {
            // log
            throw new ServiceException(e);
        }
    }

    @Override
    public void authorization(User user) throws ServiceException {
        try {
            if (!userDAO.findUser(user)) {
                throw new DAOException();
            }
        } catch (DAOException e) {
            // log
            throw new ServiceException(e);
        }
    }

    @Override
    public List<String> listAllUsers() throws ServiceException {
        List<String> userList = userDAO.findAllLogin();

        try {
            if (userList == null || userList.isEmpty()) {
                throw new DAOException();
            } else {
                return userList;
            }
        } catch (DAOException e) {
            // log
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean search(User user) throws ServiceException {
        return userDAO.findUser(user);
    }
}
