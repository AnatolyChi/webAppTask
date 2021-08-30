package com.example.webapptask.controller.impl;

import com.example.webapptask.bean.RegistrationInfo;
import com.example.webapptask.controller.Command;
import com.example.webapptask.controller.validator.UserValidator;
import com.example.webapptask.controller.validator.UserValidatorImpl;
import com.example.webapptask.service.exception.ServiceException;
import com.example.webapptask.service.ServiceProvider;
import com.example.webapptask.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;

import java.io.IOException;

@Log4j2
public class RegistrationNewUser implements Command {

    private static final ServiceProvider PROVIDER = ServiceProvider.getInstance();
    private static final UserService USER_SERVICE = PROVIDER.getUserService();
    private static final UserValidator USER_VALIDATOR = UserValidatorImpl.getValidator();

    private static final String MAIN_PAGE = "/WEB-INF/view/main.jsp";
    private static final String GO_TO_REGISTRATION_PAGE = "/WEB-INF/view/registration.jsp";
    private static final String UNKNOWN_COMMAND = "controller?command=UNKNOWN_COMMAND";
    private static final String ERROR_MESSAGE_REG_PARAM = "reg_message";
    private static final String ERROR_MESSAGE_VALID_PARAM = "valid_message";
    private static final String SUCCESSFUL_MESSAGE_PARAM = "suc_message";

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RegistrationInfo info = new RegistrationInfo(req);

        if (littleValidate(info) || USER_VALIDATOR.validParamInfo(info)) {
            req.setAttribute(ERROR_MESSAGE_VALID_PARAM, ERROR_MESSAGE_VALID_PARAM);
            req.getRequestDispatcher(GO_TO_REGISTRATION_PAGE).forward(req, resp);
            return;
        }

        try {
            if (USER_SERVICE.registration(info)) {
                req.setAttribute(SUCCESSFUL_MESSAGE_PARAM, SUCCESSFUL_MESSAGE_PARAM);
                req.getRequestDispatcher(MAIN_PAGE).forward(req, resp);
            } else {
                req.setAttribute(ERROR_MESSAGE_REG_PARAM, ERROR_MESSAGE_REG_PARAM);
                req.getRequestDispatcher(GO_TO_REGISTRATION_PAGE).forward(req, resp);
            }
        } catch (ServiceException e) {
            log.error("registration error", e);
            resp.sendRedirect(UNKNOWN_COMMAND);
        }
    }

    private boolean littleValidate(RegistrationInfo info) {
        return info.getLogin() == null || info.getLogin().isEmpty() ||
                info.getPassword() == null || info.getPassword().isEmpty();
    }
}
