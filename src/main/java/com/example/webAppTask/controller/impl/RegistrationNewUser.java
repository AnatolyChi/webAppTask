package com.example.webAppTask.controller.impl;

import com.example.webAppTask.beans.User;
import com.example.webAppTask.controller.Command;
import com.example.webAppTask.model.Model;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class RegistrationNewUser implements Command {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        User user = new User(login, password);

        Model model = Model.getInstance();
        req.setAttribute("user", user);

        if (model.isUser(user)) {
            req.getRequestDispatcher("/WEB-INF/views/registration.jsp").forward(req, resp);
        } else {
            model.addUser(user);
            req.getRequestDispatcher("/index.jsp").forward(req, resp);
        }
    }
}
