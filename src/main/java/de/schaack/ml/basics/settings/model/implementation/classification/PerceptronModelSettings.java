package de.schaack.ml.basics.settings.model.implementation.classification;

import de.schaack.ml.basics.functions.activation.implementation.SigmoidActivation;
import de.schaack.ml.basics.functions.activation.interfaces.ActivationFunction;
import de.schaack.ml.basics.settings.model.interfaces.ModelSettings;

/**
 * Implementation of {@link ActivationSettings} for a Perceptron model.
 */
public class PerceptronModelSettings implements ModelSettings {

    private int numberOfEpochs = 1;
    private ActivationFunction activationFunction = new SigmoidActivation();

    public static final String PARAM_LEARNING_RATE = "learningRate";
    public static final String PARAM_NUMBER_OF_EPOCHS = "numberOfEpochs";

    /**
     * {@inheritDoc}
     */
    @Override
    public PerceptronModelSettings setActivationFunction(ActivationFunction activationFunction) {
        this.activationFunction = activationFunction;
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ActivationFunction getActivationFunction() {
        return this.activationFunction;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer getNumberOfEpochs() {
        return this.numberOfEpochs;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ModelSettings setNumberOfEpochs(Integer epochs) {
        this.numberOfEpochs = epochs;
        return this;
    }
}
