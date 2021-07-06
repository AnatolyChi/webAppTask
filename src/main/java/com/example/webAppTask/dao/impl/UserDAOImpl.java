package com.example.webAppTask.dao.impl;

import com.example.webAppTask.bean.User;
import com.example.webAppTask.dao.UserDAO;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

// Здесь должны быть SQL запросы к бд
public class UserDAOImpl implements UserDAO {
    private final List<User> userList = new ArrayList<>();

    @Override
    public List<User> findById() {
        return null;
    }

    @Override
    public List<User> findByName() {
        return null;
    }

    @Override
    public void add(User user) {
        userList.add(user);
    }

    @Override
    public void update(User user) {

    }

    @Override
    public void delete(User user) {
        userList.remove(user);
    }

    @Override
    public boolean findUser(User user) {
        return userList.contains(user);
    }

    @Override
    public List<String> findAllLogin() {
        return userList.stream()
                .map(User::getLogin)
                .collect(Collectors.toList());
    }
}
