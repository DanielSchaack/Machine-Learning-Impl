package de.schaack.ml.basics.config.logging;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import de.schaack.ml.basics.config.properties.ConfigReader;

public class LoggerConfig {
    private static Integer logBufferSize = 300;
    private static BufferingLogHandler bufferingHandler = new BufferingLogHandler(logBufferSize);
    private static boolean useConsole = true;
    private static Level logLevel = Level.INFO;
    private static String logFolderName = "logs";
    private static String logFileName = "application.log";

    static {
        String useConsolePropertyString = ConfigReader.getProperty("ml-basics.logger.useConsole");
        if (useConsolePropertyString != null) {
            try {
                useConsole = Boolean.parseBoolean(useConsolePropertyString);
            } catch (Exception e) {
                System.err.println("useConsole must be a boolean: " + useConsolePropertyString);
            }
        }

        String buffersizePropertyString = ConfigReader.getProperty("ml-basics.logger.buffersize");
        if (buffersizePropertyString != null) {
            try {
                logBufferSize = Integer.parseInt(buffersizePropertyString);
                bufferingHandler = new BufferingLogHandler(logBufferSize);
            } catch (Exception e) {
                System.err.println("buffersize must be an int: " + buffersizePropertyString);
            }
        }

        String levelPropertyString = ConfigReader.getProperty("ml-basics.logger.level");
        if (levelPropertyString != null) {
            try {
                logLevel = Level.parse(levelPropertyString);
                bufferingHandler = new BufferingLogHandler(logBufferSize);
            } catch (Exception e) {
                System.err.println("level must be a Level: " + buffersizePropertyString);
            }
        }

        String foldernamePropertyString = ConfigReader.getProperty("ml-basics.logger.foldername");
        logFolderName = (foldernamePropertyString != null) ? foldernamePropertyString : logFolderName;

        String filenamePropertyString = ConfigReader.getProperty("ml-basics.logger.filename");
        logFileName = (filenamePropertyString != null) ? filenamePropertyString : logFileName;
    }

    private LoggerConfig() {
    }

    public static void configureLogger(String loggerName) {
        Logger logger = Logger.getLogger(loggerName);
        logger.setUseParentHandlers(useConsole);

        logger.setLevel(logLevel);
        bufferingHandler.setLevel(logLevel);

        logger.addHandler(bufferingHandler);

        try {
            // Create logs folder if it doesn't exist
            java.nio.file.Files.createDirectories(java.nio.file.Paths.get(logFolderName));

            // Create and add file handler
            FileHandler fileHandler = new FileHandler(logFolderName + "/" + logFileName, 1000000, 5, false);
            fileHandler.setFormatter(new SimpleFormatter());
            logger.addHandler(fileHandler);
        } catch (IOException e) {
            System.err.println("Failed to create log file: " + e.getMessage());
        }
    }

    public static BufferingLogHandler getBufferingHandler() {
        return bufferingHandler;
    }
}