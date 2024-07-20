package de.schaack.ml.basics.logging;

public interface ModelLogger {
    void info(String message);
    void warn(String message);
    void error(String message);
    void debug(String message);
    void logParameter(String name, Object value);
    void logMetric(String name, double value);
}