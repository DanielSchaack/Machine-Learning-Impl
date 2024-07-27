package de.schaack.ml.basics.settings.optimiser.interfaces;

public interface OptimiserSettings {
    /**
     * Retrieves the learning rate for the model.
     *
     * @return the learning rate.
     */
    double getLearningRate();

    /**
     * Sets the learning rate for the model.
     *
     * @param learningRate the learning rate to be set.
     * @return the updated {@link OptimiserSettings} object.
     */
    OptimiserSettings setLearningRate(double learningRate);
}
