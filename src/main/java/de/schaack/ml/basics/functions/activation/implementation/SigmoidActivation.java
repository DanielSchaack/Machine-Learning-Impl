package de.schaack.ml.basics.functions.activation.implementation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.schaack.ml.basics.functions.activation.interfaces.ActivationFunction;

public class SigmoidActivation implements ActivationFunction {

    private static final Logger log = LoggerFactory.getLogger(SigmoidActivation.class);

    private double currentValue = 0;
    private double localGradient = 0;
    private double currentGradient = 0;

    @Override
    public double activate(double value) {
        currentValue = 1.0 / (1.0 + Math.exp(boxBetween(-value, -10, 10)));
        log.debug("The input {} is activated to: {}", value, currentValue);
        return currentValue;
    }

    @Override
    public double deriveActivation(double globalDerivative) {
        localGradient = currentValue * (1 - currentValue);
        currentGradient = globalDerivative * localGradient;
        log.debug("The input {} is derived to: {} with an incoming gradient {}, which is multiplied to {}",
                currentValue, localGradient, globalDerivative, currentGradient);
        return currentGradient;
    }

    public static double boxBetween(double value, double minValue, double maxValue) {
        if (value < minValue) {
            return minValue;
        } else if (value > maxValue) {
            return maxValue;
        } else {
            return value;
        }
    }

    @Override
    public double getCurrentGradient() {
        return currentGradient;
    }

}
