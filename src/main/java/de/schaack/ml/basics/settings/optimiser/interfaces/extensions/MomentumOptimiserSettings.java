package de.schaack.ml.basics.settings.optimiser.interfaces.extensions;

import de.schaack.ml.basics.settings.optimiser.interfaces.OptimiserSettings;

public interface MomentumOptimiserSettings extends OptimiserSettings{
            /**
     * Retrieves the learning rate for the model.
     *
     * @return the learning rate.
     */
    double getMomentumRate();

    /**
     * Sets the learning rate for the model.
     *
     * @param momentum the learning rate to be set.
     * @return the updated {@link MomentumOptimiserSettings} object.
     */
    MomentumOptimiserSettings setMomentumRate(double momentum);

        /**
     * Retrieves the learning rate for the model.
     *
     * @return the learning rate.
     */
    double getMomentum();

        /**
     * Sets the learning rate for the model.
     *
     * @param momentum the momentum to be updated.
     */
    void updateMomentum(double momentum);
}
