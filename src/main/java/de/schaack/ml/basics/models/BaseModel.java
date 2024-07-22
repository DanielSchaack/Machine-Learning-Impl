package de.schaack.ml.basics.models;

import java.util.Map;

import de.schaack.ml.basics.config.logging.LoggingUtils;
import de.schaack.ml.basics.settings.interfaces.ModelSettings;

public abstract class BaseModel {

    protected ModelSettings settings;

    protected BaseModel(ModelSettings settings) {
        this.settings = settings;
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
            LoggingUtils.info(entry.getKey(), entry.getValue());
        }
    }

}
