package com.swaglabs.utils;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileInputStream;
import java.util.Collection;
import java.util.Properties;

public class PropertiesUtils {
    public static final String PROPERTIES_PATH = "src/main/resources/";

    private PropertiesUtils() {
        super();
    }

    public static Properties loadProperties() {
        Properties properties = new Properties();
        try {
            Collection<File> PropertiesFiles;
            PropertiesFiles = FileUtils.listFiles(new File(PROPERTIES_PATH), new String[]{"properties"}, true);
            PropertiesFiles.forEach(property -> {
                try {
                    properties.load(new FileInputStream(property));
                    LogsManager.info("Loaded properties file: " + property.getName());
                } catch (Exception e) {
                    LogsManager.error("Failed to load properties file: " + property.getName() + " - " + e.getMessage());
                }
                properties.putAll(System.getProperties());
                System.getProperties().putAll(properties);
            });
            LogsManager.info("Loaded properties files: " + PropertiesFiles.size());
            return properties;
        } catch (Exception e) {
            LogsManager.error("Failed to load properties file: " + e.getMessage());
            return null;
        }
    }

    public static String getPropertyValue(String Key) {
        try{
            return System.getProperty(Key);
        }catch (Exception e){
            LogsManager.error("Failed to get property value for key: " + Key + " - " + e.getMessage());
            return "";
        }
    }

}
