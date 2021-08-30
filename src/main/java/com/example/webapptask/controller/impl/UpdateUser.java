package com.example.webapptask.controller.impl;

import com.example.webapptask.bean.UpdateUserInfo;
import com.example.webapptask.bean.User;
import com.example.webapptask.controller.Command;
import com.example.webapptask.controller.validator.UserValidator;
import com.example.webapptask.controller.validator.UserValidatorImpl;
import com.example.webapptask.service.ServiceProvider;
import com.example.webapptask.service.UserService;
import com.example.webapptask.service.exception.ServiceException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;

import java.io.IOException;

@Log4j2
public class UpdateUser implements Command {

    private static final ServiceProvider SERVICE_PROVIDER = ServiceProvider.getInstance();
    private static final UserService USER_SERVICE = SERVICE_PROVIDER.getUserService();
    private static final UserValidator USER_VALIDATOR = UserValidatorImpl.getValidator();

    private static final String USER_SESSION_PARAM = "user";
    private static final String VALID_ERR_PARAM = "valid_err";
    private static final String UNKNOWN_COMMAND = "controller?command=UNKNOWN_COMMAND";
    private static final String GO_TO_PERSONAL_PAGE_COMMAND = "controller?command=GO_TO_PERSONAL_PAGE";
    private static final String GO_TO_UPDATE_PERSONAL_PAGE = "/WEB-INF/view/update_personal_page.jsp";

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UpdateUserInfo info = new UpdateUserInfo(req);

        if (USER_VALIDATOR.validUpdateInfo(info)) {
            req.setAttribute(VALID_ERR_PARAM, VALID_ERR_PARAM);
            req.getRequestDispatcher(GO_TO_UPDATE_PERSONAL_PAGE).forward(req, resp);
            return;
        }

        try {
            USER_SERVICE.personalUpdate(info);

            User user = (User) req.getSession().getAttribute(USER_SESSION_PARAM);
            user.setFirstName(info.getFirstname());
            user.setLastName(info.getLastname());
            user.setEmail(info.getEmail());
            user.setAge(info.getAge());

            resp.sendRedirect(GO_TO_PERSONAL_PAGE_COMMAND);
        } catch (ServiceException e) {
            log.error("error on update user", e);
            resp.sendRedirect(UNKNOWN_COMMAND);
        }
    }
}
