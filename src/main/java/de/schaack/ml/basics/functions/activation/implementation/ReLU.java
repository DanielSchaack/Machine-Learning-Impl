package de.schaack.ml.basics.functions.activation.implementation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.schaack.ml.basics.functions.activation.interfaces.ActivationFunction;

public class ReLU implements ActivationFunction {

    private static final Logger log = LoggerFactory.getLogger(ReLU.class);

    private double currentValue = 0;
    private double localGradient = 0;
    private double currentGradient = 0;

    @Override
    public double activate(double value) {
        currentValue = Math.max(0.0, value);
        log.debug("The input {} is activated to: {}", value, currentValue);
        return currentValue;
    }

    @Override
    public double deriveActivation(double globalDerivative) {
        localGradient = currentValue > 0 ? 1 : 0;
        currentGradient += globalDerivative * localGradient;
        log.debug("The input {} is derived to: {} with an incoming gradient {}, which is multiplied to {}",
                currentValue, localGradient, globalDerivative, currentGradient);
        return localGradient;
    }

    @Override
    public double getCurrentGradient() {
        return this.getCurrentGradient();
    }
}