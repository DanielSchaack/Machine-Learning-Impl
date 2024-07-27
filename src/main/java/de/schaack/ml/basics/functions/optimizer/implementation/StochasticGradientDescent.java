package de.schaack.ml.basics.functions.optimizer.implementation;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.schaack.ml.basics.functions.math.MatrixOperations;
import de.schaack.ml.basics.functions.optimizer.interfaces.OptimiserFunction;
import de.schaack.ml.basics.settings.optimiser.implementation.LearningRateOptimiser;

public class StochasticGradientDescent implements OptimiserFunction {

    private static final Logger log = LoggerFactory.getLogger(StochasticGradientDescent.class);

    private LearningRateOptimiser settings;

    public StochasticGradientDescent() {
        this(new LearningRateOptimiser());
    }

    public StochasticGradientDescent(double learningRate) {
        this(new LearningRateOptimiser(learningRate));
    }

    public StochasticGradientDescent(LearningRateOptimiser settings) {
        setSettings(settings);
    }

    public LearningRateOptimiser getSettings() {
        return this.settings;
    }

    public LearningRateOptimiser setSettings(LearningRateOptimiser settings) {
        this.settings = settings;
        return this.settings;
    }

    // w_t+1 = w_t - learning rate * 1/B * Derivative(Sum_b(Loss_b(w_t)))
    @Override
    public double[] calculateUpdates(double[] valuesOld, double gradient) {
        double update = settings.getLearningRate() * gradient;
        double[] newValues = MatrixOperations.valuesSubValue(valuesOld, update);
        log.debug("The old values {} recieves the update {} based on the gradient {}, resulting in the new value {}",
                Arrays.toString(valuesOld), gradient, update, Arrays.toString(newValues));
        return newValues;
    }

    @Override
    public double calculateUpdate(double valueOld, double gradient) {
        double update = settings.getLearningRate() * gradient;
        double newValue = valueOld - update;
        log.debug("The old value {} recieves the update {} based on the gradient {}, resulting in the new value {}",
                valueOld, gradient, update, newValue);
        return newValue;
    }

}
