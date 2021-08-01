package com.example.webAppTask.service.exception;

import com.example.webAppTask.dao.exception.DAOException;

public class ServiceException extends Exception {

    public ServiceException() {
        super();
    }

    public ServiceException(Exception e) {
        super(e);
    }

    public ServiceException(DAOException e) {
        super(e);
    }

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(String message, Exception e) {
        super(message, e);
    }
}
