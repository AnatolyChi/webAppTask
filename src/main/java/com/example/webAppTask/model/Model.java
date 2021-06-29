package com.example.webAppTask.model;

import com.example.webAppTask.beans.User;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Model {
    private static final Model instance = new Model();
    private final List<User> model;

    public static Model getInstance() {
        return instance;
    }

    private Model() {
        model = new ArrayList<>();
    }

    public void addUser(User user) {
        model.add(user);
    }

    public void deleteUser(User user) {
        model.remove(user);
    }

    public boolean isUser(User user) {
        return model.contains(user);
    }

    public List<String> sourceUserList() {
        return model.stream()
                .map(User::getLogin)
                .collect(Collectors.toList());
    }
}
