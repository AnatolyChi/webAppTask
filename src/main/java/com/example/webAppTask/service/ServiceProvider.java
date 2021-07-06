package com.example.webAppTask.service;

import com.example.webAppTask.service.impl.NewsServiceImpl;
import com.example.webAppTask.service.impl.UserServiceImpl;

public class ServiceProvider {
    private static final ServiceProvider instance = new ServiceProvider();
    private final UserService userService = new UserServiceImpl();
    private final NewsService newsService = new NewsServiceImpl();

    private ServiceProvider() { }

    public static ServiceProvider getInstance() {
        return instance;
    }

    public UserService getUserService() {
        return userService;
    }

    public NewsService getNewsService() {
        return newsService;
    }
}
