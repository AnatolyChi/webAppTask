package com.example.webapptask.service.impl;

import com.example.webapptask.dao.DAOProvider;
import com.example.webapptask.dao.MessageDAO;
import com.example.webapptask.dao.exception.DAOException;
import com.example.webapptask.service.MessageService;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class MessageServiceImpl implements MessageService {

    private static final DAOProvider PROVIDER = DAOProvider.getInstance();
    private static final MessageDAO MESSAGE_DAO = PROVIDER.getMESSAGE_DAO();

    @Override
    public void suggestNews(int userId, String title, String content) throws DAOException {

    }

    @Override
    public void commentNews(int userId, String content) throws DAOException {

    }
}
