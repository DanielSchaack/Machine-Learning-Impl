package de.schaack.ml.basics.models;

import java.util.Map;

import de.schaack.ml.basics.logging.DefaultModelLogger;
import de.schaack.ml.basics.logging.ModelLogger;
import de.schaack.ml.basics.models.settings.ModelSettings;

public abstract class BaseModel {

    protected ModelSettings settings;
    private final ModelLogger logger;

    protected BaseModel(String className, ModelSettings settings) {
        this.logger = new DefaultModelLogger(className);
        this.settings = settings;
    }

    public ModelLogger getLogger() {
        return logger;
    }

    public void setSettings(ModelSettings settings) {
        this.settings = settings;
        logSettings();
    }

    public ModelSettings getSettings() {
        return settings;
    }

    protected void logSettings() {
        for (Map.Entry<String, Object> entry : settings.getParameters().entrySet()) {
            logger.logParameter(entry.getKey(), entry.getValue());
        }
    }

}
