package com.example.webAppTask.controller.impl;

import com.example.webAppTask.controller.Command;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class GoToAuthorizationPage implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("auth", "auth");
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/authorization.jsp");
        rd.forward(request, response);
    }
}
