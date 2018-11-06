package com.robot.automation.utils;



import com.robot.automation.utils.exceptions.PropertyNotFoundException;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

public class Property {
    private static Property instance;
    private Properties prop;

    private Property() {
        readProperty("src/main/resources/prop.properties");
    }

    public static Property getInstance() {
        if (instance == null) {
            instance = new Property();
        }
        return instance;
    }

    public void readProperty(String filePath) {
        try (BufferedReader is = new BufferedReader(
                new InputStreamReader(new FileInputStream(filePath), StandardCharsets.UTF_8))) {
            prop = new Properties();
            prop.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getPropertyByKey(String key) {
        String value = prop.getProperty(key);
        if (value == null)
            throw new PropertyNotFoundException("Can`t found property value by key: "+ key );
        return prop.getProperty(key);
    }
}
