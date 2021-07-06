package com.example.webAppTask.dao;

import com.example.webAppTask.bean.News;

import java.util.List;

public interface NewsDAO {
    void add(News news);
    void update(News news);
    List<News> findAll();
    List<String> titleAll();
    boolean findNews(News news);
}
