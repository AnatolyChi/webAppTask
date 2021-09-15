package com.example.webapptask.dao.impl;

import com.example.webapptask.dao.MessageDAO;
import com.example.webapptask.dao.exception.DAOException;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class MessageDAOImpl implements MessageDAO {

    @Override
    public void addSuggest(int userId, String title, String content) throws DAOException {

    }

    @Override
    public void addComment(int userId, String content) throws DAOException {

    }
}
