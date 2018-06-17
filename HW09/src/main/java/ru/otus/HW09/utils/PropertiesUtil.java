package ru.otus.HW09.utils;

import java.util.Properties;
import java.io.FileInputStream;
import java.io.IOException;


public class PropertiesUtil {
    public static final String LOCAL_PROPERTIES_SUFFIX = ".local";

    private Properties prop;
    private Properties localProp;

    public PropertiesUtil(String configFilePath) throws IOException {
        // Main config
        prop = new Properties();
        FileInputStream configFileInputStream = new FileInputStream(configFilePath);
        prop.load(configFileInputStream);

        // Local config
        localProp = new Properties();
        try {
            FileInputStream localConfigFileInputStream = new FileInputStream(
                configFilePath + LOCAL_PROPERTIES_SUFFIX);
            localProp.load(localConfigFileInputStream);
        } catch (IOException e) {

        }
    }

    public String get(String name) {
        return localProp.getProperty(
            name,
            prop.getProperty(
                name,
                null
            )
        );
    }
}