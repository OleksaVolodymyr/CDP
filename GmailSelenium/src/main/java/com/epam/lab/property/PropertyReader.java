package com.epam.lab.property;


import com.epam.lab.exception.PropertyNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

public class PropertyReader {
    private static final Logger LOG = LoggerFactory.getLogger(PropertyReader.class);
    private static PropertyReader instance;
    private Properties prop;

    private PropertyReader() {
        readProperty("./resources/config.property");
    }

    public static PropertyReader getInstance() {
        if (instance == null) {
            instance = new PropertyReader();
        }
        return instance;
    }

    public void readProperty(String filePath) {
        try (BufferedReader is = new BufferedReader(
                new InputStreamReader(new FileInputStream(filePath), StandardCharsets.UTF_8))) {
            prop = new Properties();
            prop.load(is);
        } catch (IOException e) {
            LOG.error(e.getMessage());
        }
    }

    public String getPropertyByKey(String key) {
        String value = prop.getProperty(key);
        if (value == null)
            throw new PropertyNotFoundException("Can`t found property value by key: " + key);
        return prop.getProperty(key);
    }
}
