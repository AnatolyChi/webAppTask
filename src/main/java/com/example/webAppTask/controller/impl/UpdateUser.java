package com.example.webAppTask.controller.impl;

import com.example.webAppTask.bean.User;
import com.example.webAppTask.controller.Command;
import com.example.webAppTask.service.ServiceProvider;
import com.example.webAppTask.service.UserService;
import com.example.webAppTask.service.exception.ServiceException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
public class UpdateUser implements Command {
    private static final ServiceProvider SERVICE_PROVIDER = ServiceProvider.getInstance();
    private static final UserService USER_SERVICE = SERVICE_PROVIDER.getUSER_SERVICE();

    private static final String USER_SESSION = "user";
    private static final String FIRST_NAME_PARAM = "firstname";
    private static final String LAST_NAME_PARAM = "lastname";
    private static final String EMAIL_PARAM = "email";
    private static final String AGE_PARAM = "age";
    private static final String LOG_ERROR = "unknown command";
    private static final String UNKNOWN_COMMAND = "controller?command=UNKNOWN_COMMAND";
    private static final String PERSONAL_PAGE_COMMAND = "controller?command=PERSONAL_PAGE";

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String firstName = req.getParameter(FIRST_NAME_PARAM);
        String lastName = req.getParameter(LAST_NAME_PARAM);
        String email = req.getParameter(EMAIL_PARAM);
        String age = req.getParameter(AGE_PARAM);
        User user = (User) req.getSession().getAttribute(USER_SESSION);

        try {
            USER_SERVICE.personalUpdate(user, firstName, lastName, email, age);
            resp.sendRedirect(PERSONAL_PAGE_COMMAND);
        } catch (ServiceException e) {
            log.error(LOG_ERROR, e);
            e.printStackTrace();
            resp.sendRedirect(UNKNOWN_COMMAND);
        }
    }
}
