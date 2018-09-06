package ru.otus.HW13.servlet;

import ru.otus.HW09.models.UserDataSet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ActionServlet extends ContextServlet {

    private int id = 0;

    public ActionServlet() {

    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String login = (String) request.getSession().getAttribute("login");

        if(login != null) {
            UserDataSet user = new UserDataSet("John", 25);
            dbService.save(user);
            dbService.read(id);
            dbService.read(id++);
        } else {
            response.sendRedirect("/");
        }
    }

}