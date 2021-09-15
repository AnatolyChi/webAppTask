package com.example.webapptask.controller.impl;

import com.example.webapptask.controller.Command;
import com.example.webapptask.service.MessageService;
import com.example.webapptask.service.ServiceProvider;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class WriteToAdmin implements Command {

    private static final ServiceProvider SERVICE_PROVIDER = ServiceProvider.getInstance();
    private static final MessageService MESSAGE_SERVICE = SERVICE_PROVIDER.getMessageService();

    private static final String USER_ID_PARAM = "user_id";
    private static final String TITLE_PARAM = "title";
    private static final String CONTENT_PARAM = "content";

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int userId = Integer.parseInt(req.getParameter(USER_ID_PARAM));
        String title = req.getParameter(TITLE_PARAM);
        String content = req.getParameter(CONTENT_PARAM);
    }
}
