package org.project;

import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {
    Properties properties;
    public ConfigReader() {
        loadProperties();
    }

    public void loadProperties() {
        properties = new Properties();
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("Config.properties"))  {

            if (input != null) {
                properties.load(input);
            } else {
                throw new RuntimeException("config.properties file not found in the classpath.");
            }
        } catch (Exception e) {
            throw new RuntimeException("config.properties file not found in the classpath.");
        }
    }

    public String webUrl(){
        System.out.println(properties.getProperty("url"));
        return properties.getProperty("url");
    }
    public String productName(){
        return properties.getProperty("productName");
    }
    public String getMobileNumber(){
        return properties.getProperty("number");
    }


}