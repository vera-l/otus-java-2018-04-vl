package ru.otus.HW13.servlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginServlet extends HttpServlet {
    private static final String LOGIN_PARAMETER_NAME = "login";
    private static final String PASSWORD_PARAMETER_NAME = "password";

    private static final String LOGIN = "root";
    private static final String PASSWORD = "toor";

    private static final String COOKIE_NAME = "session";

    public LoginServlet() { }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String login = request.getParameter(LOGIN_PARAMETER_NAME);
        String password = request.getParameter(PASSWORD_PARAMETER_NAME);

        if(login.equals(LOGIN) && password.equals(PASSWORD)) {
            request.getSession().setAttribute(
                "login",
                login
            );
        }
        response.sendRedirect("/");
    }

}