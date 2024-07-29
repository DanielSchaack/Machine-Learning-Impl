package de.schaack.ml.basics.settings.model.interfaces;

import de.schaack.ml.basics.data.interfaces.DataPreprocessor;
import de.schaack.ml.basics.data.interfaces.DataSplitter;
import de.schaack.ml.basics.functions.activation.interfaces.ActivationFunction;

/**
 * Defines the settings for a machine learning model, allowing for the
 * configuration of various parameters.
 */
public interface ModelSettings {

    /**
     * Sets the activation function for the model.
     *
     * @param activationFunction the activation function to be set.
     * @return the updated {@link ModelSettings} object.
     */
    ModelSettings setActivationFunction(ActivationFunction activationFunction);

    /**
     * Retrieves the activation function for the model.
     *
     * @return the activation function.
     */
    ActivationFunction getActivationFunction();

    /**
     * Sets the dataPreprocessor for the model.
     *
     * @param dataPreprocessor the dataPreprocessor to be set.
     * @return the updated {@link ModelSettings} object.
     */
    ModelSettings setDataPreprocessor(DataPreprocessor dataPreprocessor);

    /**
     * Retrieves the preprocessor for the model.
     *
     * @return the preprocessor.
     */
    DataPreprocessor getDataPreprocessor();

    /**
     * Retrieves the amount of epochs during training of the model.
     *
     * @return the loss function.
     */
    Integer getNumberOfEpochs();

    /**
     * Sets the amount of epochs during training of the model.
     *
     * @param epochs the amount of epochs to be set.
     * @return the updated {@link ModelSettings} object.
     */
    ModelSettings setNumberOfEpochs(Integer epochs);

}