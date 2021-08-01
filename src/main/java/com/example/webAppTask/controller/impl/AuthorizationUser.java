package com.example.webAppTask.controller.impl;

import com.example.webAppTask.bean.RegistrationInfo;
import com.example.webAppTask.bean.User;
import com.example.webAppTask.controller.Command;
import com.example.webAppTask.service.exception.ServiceException;
import com.example.webAppTask.service.ServiceProvider;
import com.example.webAppTask.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.Optional;

@Slf4j
public class AuthorizationUser implements Command {
    private static final ServiceProvider provider = ServiceProvider.getInstance();
    private static final UserService userService = provider.getUserService();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        RegistrationInfo info = new RegistrationInfo(login, password);

        try {
            Optional<User> userOptional = userService.authorization(info);

            if (userOptional.isPresent()) {
                HttpSession session = req.getSession(true);
                session.setAttribute("user", userOptional.get());
                req.setAttribute("greeting", "Welcome " + login);
                resp.sendRedirect("controller?command=MAIN_PAGE");
            } else {
                req.setAttribute("auth", "auth");
                req.setAttribute("message", "Enter the correct values!");
                req.getRequestDispatcher("/WEB-INF/views/authorization.jsp").forward(req, resp);
            }
        } catch (ServiceException e) {
            log.error("unknown command", e);
            e.printStackTrace();
            resp.sendRedirect("controller?command=UNKNOWN_COMMAND");
        }
    }
}
