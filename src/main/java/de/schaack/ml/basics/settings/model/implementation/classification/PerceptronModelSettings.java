package de.schaack.ml.basics.settings.model.implementation.classification;

import de.schaack.ml.basics.functions.activation.implementation.SigmoidActivation;
import de.schaack.ml.basics.functions.activation.interfaces.ActivationFunction;
import de.schaack.ml.basics.functions.optimizer.implementation.StochasticGradientDescent;
import de.schaack.ml.basics.functions.optimizer.interfaces.OptimiserFunction;
import de.schaack.ml.basics.settings.model.interfaces.ModelSettings;

/**
 * Implementation of {@link ActivationSettings} for a Perceptron model.
 */
public class PerceptronModelSettings implements ModelSettings {

    private int numberOfEpochs = 1;
    private ActivationFunction activationFunction = new SigmoidActivation();
    private OptimiserFunction optimiserFunction = new StochasticGradientDescent(0.1);

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
     * 
     * Retrieves the optimiser function for the model.
     *
     * @return the optimiser function.
     */
    public OptimiserFunction getOptimiserFunction() {
        return this.optimiserFunction;
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
