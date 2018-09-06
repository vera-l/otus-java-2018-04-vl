package ru.otus.HW12;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.ContextHandler;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import ru.otus.HW09.DBService;
import ru.otus.HW09.models.UserDataSet;
import ru.otus.HW11.cache.CacheEngine;
import ru.otus.HW12.servlet.ActionServlet;
import ru.otus.HW12.servlet.LoginServlet;
import ru.otus.HW12.servlet.LogoutServlet;
import ru.otus.HW12.servlet.StatisticsServlet;

import java.io.IOException;

public class WebServer {

    private final static String PUBLIC_HTML = "public_html";

    private final Server server;
    private final CacheEngine<Integer, UserDataSet> cacheEngine;

    public WebServer(CacheEngine cacheEngine, DBService dbService, int port) throws IOException {
        this.cacheEngine = cacheEngine;

        ResourceHandler resourceHandler = new ResourceHandler();
        resourceHandler.setResourceBase(PUBLIC_HTML);
        ContextHandler resourceContextHandler = new ContextHandler("/static");
        resourceContextHandler.setHandler(resourceHandler);

        ServletContextHandler contextHandler = new ServletContextHandler(ServletContextHandler.SESSIONS);
        TemplateProcessor templateProcessor = new TemplateProcessor();

        contextHandler.addServlet(
            LoginServlet.class,
            "/login"
        );
        contextHandler.addServlet(
            LogoutServlet.class,
            "/logout"
        );
        contextHandler.addServlet(
            new ServletHolder(
                new ActionServlet(dbService)
            ),
            "/action"
        );
        contextHandler.addServlet(
            new ServletHolder(
                new StatisticsServlet(cacheEngine, templateProcessor)
            ),
            "/"
        );

        this.server = new Server(port);
        server.setHandler(
            new HandlerList(
                resourceContextHandler,
                contextHandler
            )
        );

    }

    public void start() throws Exception {
        server.start();
        server.join();
    }

    public void stop() throws Exception {
        server.stop();
    }

}