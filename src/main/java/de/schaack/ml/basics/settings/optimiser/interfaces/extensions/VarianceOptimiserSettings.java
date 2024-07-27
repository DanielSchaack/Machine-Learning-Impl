package de.schaack.ml.basics.settings.optimiser.interfaces.extensions;

public interface VarianceOptimiserSettings {
                /**
     * Retrieves the learning rate for the model.
     *
     * @return the learning rate.
     */
    double getVarianceRate();

    /**
     * Sets the learning rate for the model.
     *
     * @param variance the learning rate to be set.
     * @return the updated {@link VarianceOptimiserSettings} object.
     */
    VarianceOptimiserSettings setVarianceRate(double variance);

        /**
     * Retrieves the learning rate for the model.
     *
     * @return the learning rate.
     */
    double getVariance();

        /**
     * Sets the learning rate for the model.
     *
     * @param variance the variance to be updated.
     */
    void updateVariance(double variance);
}
