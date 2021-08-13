package com.example.webAppTask.service;

import com.example.webAppTask.service.impl.NewsServiceImpl;
import com.example.webAppTask.service.impl.UserServiceImpl;

public class ServiceProvider {
    private static final ServiceProvider INSTANCE = new ServiceProvider();
    private final UserService USER_SERVICE = new UserServiceImpl();
    private final NewsService NEWS_SERVICE = new NewsServiceImpl();

    private ServiceProvider() { }

    public static ServiceProvider getInstance() {
        return INSTANCE;
    }

    public UserService getUSER_SERVICE() {
        return USER_SERVICE;
    }

    public NewsService getNEWS_SERVICE() {
        return NEWS_SERVICE;
    }
}
