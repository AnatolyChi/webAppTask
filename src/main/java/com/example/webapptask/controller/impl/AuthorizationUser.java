package com.example.webapptask.controller.impl;

import com.example.webapptask.bean.RegistrationInfo;
import com.example.webapptask.bean.User;
import com.example.webapptask.controller.Command;
import com.example.webapptask.controller.validator.UserValidator;
import com.example.webapptask.controller.validator.UserValidatorImpl;
import com.example.webapptask.service.exception.ServiceException;
import com.example.webapptask.service.ServiceProvider;
import com.example.webapptask.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.log4j.Log4j2;

import java.io.IOException;
import java.util.Optional;

@Log4j2
public class AuthorizationUser implements Command {

    private static final ServiceProvider PROVIDER = ServiceProvider.getInstance();
    private static final UserService USER_SERVICE = PROVIDER.getUserService();
    private static final UserValidator USER_VALIDATOR = UserValidatorImpl.getValidator();

    private static final String USER_SESSION = "user";
    private static final String MAIN_PAGE = "controller?command=MAIN_PAGE";
    private static final String GO_TO_AUTHORIZATION_PAGE = "/WEB-INF/view/authorization.jsp";
    private static final String UNKNOWN_COMMAND = "controller?command=UNKNOWN_COMMAND";
    private static final String MESSAGE_PARAM = "message";

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RegistrationInfo info = new RegistrationInfo(req);

        if (littleValidate(info) || USER_VALIDATOR.validParamInfo(info)) {
            req.setAttribute(MESSAGE_PARAM, MESSAGE_PARAM);
            req.getRequestDispatcher(GO_TO_AUTHORIZATION_PAGE).forward(req, resp);
            return;
        }

        try {
            System.err.println("hello i am error");
            Optional<User> userOptional = USER_SERVICE.authorization(info);

            if (userOptional.isPresent()) {
                HttpSession session = req.getSession();
                session.setAttribute(USER_SESSION, userOptional.get());
                resp.sendRedirect(MAIN_PAGE);
            } else {
                req.setAttribute(MESSAGE_PARAM, MESSAGE_PARAM);
                req.getRequestDispatcher(GO_TO_AUTHORIZATION_PAGE).forward(req, resp);
            }

        } catch (ServiceException e) {
            log.error("authorization error", e);
            resp.sendRedirect(UNKNOWN_COMMAND);
        }
    }

    private boolean littleValidate(RegistrationInfo info) {
        return info.getLogin() == null || info.getLogin().isEmpty() ||
                info.getPassword() == null || info.getPassword().isEmpty();
    }
}
