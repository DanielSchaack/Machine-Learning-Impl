package de.schaack.ml.basics.functions.optimizer.implementation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
    public double calculateUpdate(double valueOld, int batchSize, double gradient) {
        double update = settings.getLearningRate() * (1.0 / batchSize) * gradient;
        double newValue = valueOld - update;
        log.debug(
                "The old value {} recieves the update {} based on the gradient {} from a batch of size {}, resulting in the new value {}",
                valueOld, gradient, batchSize, update, newValue);
        return newValue;
    }

}
