package com.example.webAppTask.controller.impl;

import com.example.webAppTask.controller.Command;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class GoToRegistrationPage implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("reg", "reg");
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/registration.jsp");
        rd.forward(request, response);
    }
}
