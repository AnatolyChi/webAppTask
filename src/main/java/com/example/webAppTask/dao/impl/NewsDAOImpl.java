package com.example.webAppTask.dao.impl;

import com.example.webAppTask.bean.News;
import com.example.webAppTask.dao.NewsDAO;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

// Здесь должны быть SQL запросы к бд
public class NewsDAOImpl implements NewsDAO {
    private final List<News> newsList = new ArrayList<>();

    @Override
    public void add(News news) {
        newsList.add(news);
    }

    @Override
    public void update(News news) {

    }

    @Override
    public boolean findNews(News news) {
        return newsList.contains(news);
    }

    @Override
    public List<News> findAll() {
        return newsList;
    }

    @Override
    public List<String> titleAll() {
        return newsList.stream()
                .map(News::getTitle)
                .collect(Collectors.toList());
    }


}
