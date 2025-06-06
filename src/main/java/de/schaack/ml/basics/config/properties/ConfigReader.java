package de.schaack.ml.basics.config.properties;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Logger;

public class ConfigReader {
    private static final String[] CONFIG_FILES = { "application.yaml", "application.yml", "application.properties" };
    private static Properties properties;
    private static Logger globalLogger = Logger.getGlobal();

    private ConfigReader() {
    }

    static {
        loadConfig();
    }

    public static void loadConfig() {
        globalLogger.info("Start loading configuration");
        for (String fileName : CONFIG_FILES) {
            loadConfigs(fileName);
        }
        globalLogger.warning("No valid configuration file found, using defaults");
    }

    private static void loadConfigs(String fileName){
        try (InputStream input = ConfigReader.class.getClassLoader().getResourceAsStream(fileName)) {
            if (input != null) {
                if (fileName.endsWith(".yaml") || fileName.endsWith(".yml")) {
                    properties = YamlParser.loadProperties(input);
                } else if (fileName.endsWith(".properties")) {
                    properties.load(input);
                }
                globalLogger.info("Loaded configuration from: " + fileName);
            }
        } catch (IOException ex) {
            globalLogger.severe("Error loading " + fileName + ": " + ex.getMessage());
        }
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }

    public static Properties getProperties() {
        return properties;
    }
}