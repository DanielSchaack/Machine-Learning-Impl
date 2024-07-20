package de.schaack.ml.basics.logging;

import java.util.logging.Logger;

import de.schaack.ml.basics.config.logging.LoggerUtil;

public class DefaultModelLogger implements ModelLogger {

    private Logger log = LoggerUtil.getLogger(DefaultModelLogger.class.getName());

    public DefaultModelLogger(String className) {
        this.log = LoggerUtil.getLogger(className);
    }

    @Override
    public void info(String message) {
        log.info(message);
    }

    @Override
    public void warn(String message) {
        log.warning(message);
    }

    @Override
    public void error(String message) {
        log.severe(message);
    }

    @Override
    public void debug(String message) {
        log.fine(message);
    }

    @Override
    public void logParameter(String name, Object value) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'logParameter'");
    }

    @Override
    public void logMetric(String name, double value) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'logMetric'");
    }
}
