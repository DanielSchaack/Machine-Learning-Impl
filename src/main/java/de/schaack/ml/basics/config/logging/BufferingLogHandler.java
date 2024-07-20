package de.schaack.ml.basics.config.logging;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Handler;
import java.util.logging.LogRecord;
import java.util.logging.SimpleFormatter;

public class BufferingLogHandler extends Handler {
    private final List<String> buffer;
    private final int capacity;

    public BufferingLogHandler(int capacity) {
        this.buffer = new ArrayList<>();
        this.capacity = capacity;
        setFormatter(new SimpleFormatter());
    }

    @Override
    public void publish(LogRecord logRecord) {
        String message = getFormatter().format(logRecord);
        synchronized (buffer) {
            if (buffer.size() >= capacity) {
                buffer.remove(0);
            }
            buffer.add(message);
        }
    }

    @Override
    public void flush() {
        // No need to implement for buffering
    }

    @Override
    public void close() throws SecurityException {
        buffer.clear();
    }

    public List<String> getBuffer() {
        synchronized (buffer) {
            return new ArrayList<>(buffer);
        }
    }
}
