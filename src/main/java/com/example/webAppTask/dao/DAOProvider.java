package com.example.webAppTask.dao;

import com.example.webAppTask.dao.impl.NewsDAOImpl;
import com.example.webAppTask.dao.impl.UserDAOImpl;

public class DAOProvider {
    private static final DAOProvider INSTANCE = new DAOProvider();
    private final UserDAO userDAO = new UserDAOImpl();
    private final NewsDAO newsDAO = new NewsDAOImpl();

    private DAOProvider() { }

    public static DAOProvider getInstance() {
        return INSTANCE;
    }

    public UserDAO getUserDAO() {
        return userDAO;
    }

    public NewsDAO getNewsDAO() {
        return newsDAO;
    }
}
