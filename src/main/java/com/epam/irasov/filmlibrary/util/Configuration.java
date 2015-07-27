package com.epam.irasov.filmlibrary.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Configuration {

        private static Configuration instance = null;
        private Properties props = new Properties();
        private static final String FILE_NAME_SETTINGS = "config.properties";

        private Configuration() {

        }

    private static class configurationHolder {
        private final static Configuration instance = new Configuration();
    }

    public static Configuration getInstance() {
        return configurationHolder.instance;
    }

        public void loadConfiguration() throws IOException {
            InputStream stream = Configuration.class.getClassLoader().getResourceAsStream(FILE_NAME_SETTINGS);
            props.load(stream);
        }

        public String getProperties(String key) {
            return props.getProperty(key);
        }
    }

