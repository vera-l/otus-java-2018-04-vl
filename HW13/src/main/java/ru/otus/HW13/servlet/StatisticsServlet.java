package ru.otus.HW13.servlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;


public class StatisticsServlet extends ContextServlet {

    private final String LOGIN_TPL_NAME = "login.tpl";
    private final String STATISTICS_TPL_NAME = "statistics.tpl";

    public StatisticsServlet() {

    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String page;
        String login = (String) request.getSession().getAttribute("login");

        if(login != null) {
            HashMap<String, Object> dataModel = cacheEngine.getStatistics();
            dataModel.put("login", login);

            page = templateProcessor.getPage(
                STATISTICS_TPL_NAME,
                dataModel
            );
        } else {
            page = templateProcessor.getPage(
                LOGIN_TPL_NAME,
                null
            );
        }

        response.getWriter().println(page);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        doGet(request, response);
    }

}