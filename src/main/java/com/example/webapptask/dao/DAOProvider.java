package com.example.webapptask.dao;

import com.example.webapptask.dao.impl.MessageDAOImpl;
import com.example.webapptask.dao.impl.NewsDAOImpl;
import com.example.webapptask.dao.impl.UserDAOImpl;

public class DAOProvider {
    private static final DAOProvider INSTANCE = new DAOProvider();
    private final UserDAO USER_DAO = new UserDAOImpl();
    private final NewsDAO NEWS_DAO = new NewsDAOImpl();
    private final MessageDAO MESSAGE_DAO = new MessageDAOImpl();

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

    public MessageDAO getMESSAGE_DAO() {
        return MESSAGE_DAO;
    }
}
