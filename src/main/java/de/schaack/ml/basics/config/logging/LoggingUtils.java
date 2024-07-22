package de.schaack.ml.basics.config.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggingUtils {
    private LoggingUtils() {
    }

    private static final Logger logger = LoggerFactory.getLogger(LoggingUtils.class);

    public static void info(String message) {
        logger.info(message);
    }

    public static void info(String message, Object arg) {
        logger.info(message, arg);
    }

    public static void debug(String message) {
        logger.debug(message);
    }

    public static void debug(String message, Object arg) {
        logger.debug(message, arg);
    }

    public static void error(String message) {
        logger.error(message);
    }

    public static void error(String message, Throwable t) {
        logger.error(message, t);
    }
}
