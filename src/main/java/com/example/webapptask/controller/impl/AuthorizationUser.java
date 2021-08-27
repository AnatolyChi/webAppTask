package com.example.webapptask.controller.impl;

import com.example.webapptask.bean.RegistrationInfo;
import com.example.webapptask.bean.User;
import com.example.webapptask.controller.Command;
import com.example.webapptask.service.exception.ServiceException;
import com.example.webapptask.service.ServiceProvider;
import com.example.webapptask.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.Optional;

@Slf4j
public class AuthorizationUser implements Command {
    private static final ServiceProvider PROVIDER = ServiceProvider.getInstance();
    private static final UserService USER_SERVICE = PROVIDER.getUSER_SERVICE();

    private static final String USER_SESSION = "user";
    private static final String MESSAGE_ERROR_PARAM = "message";
    private static final String MAIN_PAGE_COMMAND = "controller?command=MAIN_PAGE";
    private static final String UNKNOWN_COMMAND = "controller?command=UNKNOWN_COMMAND";
    private static final String AUTHORIZATION_PAGE = "/WEB-INF/view/authorization.jsp";
    private static final String LOG_ERROR = "unknown command";

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RegistrationInfo info = new RegistrationInfo(req);

        try {
            Optional<User> userOptional = USER_SERVICE.authorization(info);

            if (userOptional.isPresent()) {
                HttpSession session = req.getSession();
                session.setAttribute(USER_SESSION, userOptional.get());
                resp.sendRedirect(MAIN_PAGE_COMMAND);
            } else {
                req.setAttribute(MESSAGE_ERROR_PARAM, MESSAGE_ERROR_PARAM);
                req.getRequestDispatcher(AUTHORIZATION_PAGE).forward(req, resp);
            }
        } catch (ServiceException e) {
            log.error(LOG_ERROR, e);
            resp.sendRedirect(UNKNOWN_COMMAND);
        }
    }
}
