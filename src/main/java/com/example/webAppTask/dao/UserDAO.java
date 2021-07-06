package com.example.webAppTask.dao;

import com.example.webAppTask.bean.User;

import java.util.List;

public interface UserDAO {
    List<String> findAllLogin();
    List<User> findById();
    List<User> findByName();
    void add(User user);
    void update(User user);
    void delete(User user);
    boolean findUser(User user);
}
