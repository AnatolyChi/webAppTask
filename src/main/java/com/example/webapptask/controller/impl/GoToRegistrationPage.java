package com.example.webapptask.controller.impl;

import com.example.webapptask.controller.Command;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class GoToRegistrationPage implements Command {

    private static final String REGISTRATION_PAGE = "/WEB-INF/view/registration.jsp";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher(REGISTRATION_PAGE).forward(request, response);
    }
}
