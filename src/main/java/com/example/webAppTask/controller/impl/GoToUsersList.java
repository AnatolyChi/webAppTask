package com.example.webAppTask.controller.impl;

import com.example.webAppTask.controller.Command;
import com.example.webAppTask.service.ServiceException;
import com.example.webAppTask.service.ServiceProvider;
import com.example.webAppTask.service.UserService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

public class GoToUsersList implements Command {
    private static final ServiceProvider provider = ServiceProvider.getInstance();
    private static final UserService userService = provider.getUserService();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            List<String> userList = userService.listAllUsers();
            req.setAttribute("userList", userList);

            RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/views/registeredUsers.jsp");
            rd.forward(req, resp);
        } catch (ServiceException e) {

            // дописать обработку...
            RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/views/main.jsp");
            rd.forward(req, resp);
        }
    }
}
