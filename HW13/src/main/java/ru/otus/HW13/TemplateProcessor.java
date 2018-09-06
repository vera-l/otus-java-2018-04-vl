package ru.otus.HW13;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;

public class TemplateProcessor {
    private static final String DIR = "/tpl/";
    private static final String ENCODING = "UTF-8";

    private final Configuration configuration;

    public TemplateProcessor() throws IOException {
        configuration = new Configuration(Configuration.VERSION_2_3_28);
        configuration.setClassForTemplateLoading(this.getClass(), DIR);
        configuration.setDefaultEncoding(ENCODING);
        configuration.setTemplateExceptionHandler(TemplateExceptionHandler.HTML_DEBUG_HANDLER);
    }

    public String getPage(String tplName, Map<String, Object> dataModel) throws IOException {
        try(Writer stream = new StringWriter()) {
            Template tpl = configuration.getTemplate(tplName);
            tpl.process(dataModel, stream);
            return stream.toString();
        } catch (TemplateException e) {
            e.printStackTrace();
        }
        return null;
    }

}