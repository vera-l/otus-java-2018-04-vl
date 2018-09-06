package ru.otus.HW13.servlet;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.otus.HW11.DBServiceCachedImpl;
import ru.otus.HW11.cache.CacheEngine;
import ru.otus.HW13.TemplateProcessor;

import javax.servlet.http.HttpServlet;

public class ContextServlet extends HttpServlet {
    private static ApplicationContext context = new ClassPathXmlApplicationContext("SpringBeans.xml");
    protected static DBServiceCachedImpl dbService = (DBServiceCachedImpl) context.getBean("dbService");
    protected static CacheEngine cacheEngine = (CacheEngine) context.getBean("cacheEngine");
    protected static TemplateProcessor templateProcessor = (TemplateProcessor) context.getBean("templateProcessor");
}
