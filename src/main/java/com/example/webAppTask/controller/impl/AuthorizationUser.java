package com.example.webAppTask.controller.impl;

import com.example.webAppTask.bean.User;
import com.example.webAppTask.controller.Command;
import com.example.webAppTask.service.ServiceException;
import com.example.webAppTask.service.ServiceProvider;
import com.example.webAppTask.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

public class AuthorizationUser implements Command {
    private static final ServiceProvider provider = ServiceProvider.getInstance();
    private static final UserService userService = provider.getUserService();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        String login = req.getParameter("login");
        String password = req.getParameter("password");
        User user = new User(login, password);

        try {
            userService.authorization(user);
            session.setAttribute("user", user);
            req.setAttribute("greeting", "Welcome " + login);
            req.getRequestDispatcher("/WEB-INF/views/main.jsp").forward(req, resp);
        } catch (ServiceException e) {
            // log
            req.setAttribute("message", "Enter the correct values!");
            req.getRequestDispatcher("/WEB-INF/views/authorization.jsp").forward(req, resp);
        }
    }
}
