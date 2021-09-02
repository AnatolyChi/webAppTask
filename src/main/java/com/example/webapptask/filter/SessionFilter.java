package com.example.webapptask.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Objects;

@WebFilter("/controller")
public class SessionFilter implements Filter {

    private static final String USER_SESSION_PARAM = "user";
    private static final String AUTHORIZATION_COMMAND = "command=GO_TO_AUTHORIZATION_PAGE";
    private static final String REGISTRATION_COMMAND = "command=GO_TO_REGISTRATION_PAGE";
    private static final String MAIN_COMMAND = "command=MAIN_PAGE";
    private static final String GO_TO_MAIN = "index.jsp";
    private static final String EMPTY = "";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession(false);

        String pathCommand = request.getQueryString();

        boolean loggedIn = session != null && session.getAttribute(USER_SESSION_PARAM) != null;
        boolean logRequest = pathCommand != null && pathCommand.equals(AUTHORIZATION_COMMAND);
        boolean regRequest = pathCommand != null && pathCommand.equals(REGISTRATION_COMMAND);
        boolean mainRequest = pathCommand != null && pathCommand.equals(MAIN_COMMAND);
        boolean emptyPath = Objects.equals(pathCommand, EMPTY) || pathCommand == null;

        if (loggedIn || logRequest || regRequest || mainRequest || emptyPath) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            response.sendRedirect(GO_TO_MAIN);
        }
    }
        @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
