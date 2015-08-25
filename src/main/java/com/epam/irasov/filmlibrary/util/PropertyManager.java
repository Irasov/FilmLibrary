package com.epam.irasov.filmlibrary.util;

import com.epam.irasov.filmlibrary.action.Action;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class PropertyManager {
    private static Logger LOGGER = Logger.getLogger(PropertyManager.class);

    Properties properties = new Properties();

    public void loadProperty(String filename) {
        try {
            properties.load(PropertyManager.class.getClassLoader().getResourceAsStream(filename));
        } catch (IOException e) {
            LOGGER.info("file not found: "+e);
        }
    }

    public Map<String,Action> getPropertyKey() {
        Map<String, Action> actionMap = new HashMap<>();
        Enumeration<?> e = properties.propertyNames();
        Class cl;
        while (e.hasMoreElements()) {
            String key = (String) e.nextElement();
            String value = properties.getProperty(key);
            try {
                cl = Class.forName(value);
                actionMap.put(key,(Action)cl.newInstance());
            } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e1) {
                LOGGER.info("map exception: "+e);
            }
        }
        return actionMap;
    }

    public String getProperties(String key) {
        return properties.getProperty(key);
    }
}
