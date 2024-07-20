package de.schaack.ml.basics.config.logging;

import java.util.logging.Logger;

public class LoggerUtil {
    public static Logger getLogger(String className) {
        LoggerConfig.configureLogger(className);
        return Logger.getLogger(className);
    }

    private LoggerUtil() {
    }
}
