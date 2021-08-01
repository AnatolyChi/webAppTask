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
public class RegistrationNewUser implements Command {
    private static final ServiceProvider provider = ServiceProvider.getInstance();
    private static final UserService userService = provider.getUserService();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RegistrationInfo info = new RegistrationInfo(req);

        try {
            Optional<User> userOptional = userService.registration(info);

            if (!userOptional.isPresent()) {
                HttpSession session = req.getSession(true);
                session.setAttribute("user", new User(info.getLogin(), info.getPassword()));
                resp.sendRedirect("controller?command=MAIN_PAGE");
            } else {
                req.setAttribute("reg", "reg");
                req.setAttribute("message", "A user with this name already exists!");
                req.getRequestDispatcher("/WEB-INF/views/registration.jsp").forward(req, resp);
            }
        } catch (ServiceException e) {
            log.error("unknown command", e);
            e.printStackTrace();
            resp.sendRedirect("controller?command=UNKNOWN_COMMAND");
        }
    }
}
