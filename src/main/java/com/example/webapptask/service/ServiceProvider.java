package com.example.webapptask.service;

import com.example.webapptask.service.impl.NewsServiceImpl;
import com.example.webapptask.service.impl.UserServiceImpl;

public class ServiceProvider {
    private static final ServiceProvider INSTANCE = new ServiceProvider();
    private final UserService USER_SERVICE = new UserServiceImpl();
    private final NewsService NEWS_SERVICE = new NewsServiceImpl();

    private ServiceProvider() { }

    public static ServiceProvider getInstance() {
        return INSTANCE;
    }

    public UserService getUserService() {
        return USER_SERVICE;
    }

    public NewsService getNewsService() {
        return NEWS_SERVICE;
    }
}
