package com.academy.helpers;

import java.io.IOException;
import java.util.Properties;

/**
 * This class is responsible for retrieving environment specific information from the property files.
 */
public class FileReader {

    private static FileReader fileReader;
    private final Properties properties;
    private static final String PROPERTY_FILE_PATH = "browser.properties";


    private FileReader() {
        properties = new Properties();
        readPropertyFile();
    }

    public static FileReader getInstance() {
        if (fileReader == null) {
            fileReader = new FileReader();
        }
        return fileReader;
    }

    public String getProperty(String keyName) {
        return properties.getProperty(keyName);
    }

    private void readPropertyFile() {
        try {
            properties.load(getClass().getClassLoader().getResourceAsStream(PROPERTY_FILE_PATH));
        } catch (IOException ioex) {
            throw new RuntimeException(ioex);
        }
    }
}
