package com.example.webapptask.dao;

import com.example.webapptask.dao.impl.NewsDAOImpl;
import com.example.webapptask.dao.impl.UserDAOImpl;

public class DAOProvider {
    private static final DAOProvider INSTANCE = new DAOProvider();
    private final UserDAO USER_DAO = new UserDAOImpl();
    private final NewsDAO NEWS_DAO = new NewsDAOImpl();

    private DAOProvider() { }

    public static DAOProvider getInstance() {
        return INSTANCE;
    }

    public UserDAO getUSER_DAO() {
        return USER_DAO;
    }

    public NewsDAO getNEWS_DAO() {
        return NEWS_DAO;
    }
}
