package de.schaack.ml.basics.functions.activation.implementation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.schaack.ml.basics.functions.activation.interfaces.ActivationFunction;

public class SigmoidActivation implements ActivationFunction {

    private static final Logger log = LoggerFactory.getLogger(SigmoidActivation.class);

    @Override
    public double activate(double value) {
        double activatedValue = 1.0 / (1.0 + Math.exp(-value));
        log.debug("The input {} is activated to: {}", value, activatedValue);
        return activatedValue;
    }

    @Override
    public double derivative(double value) {
        double derivedValue = activate(value) * (1 - activate(value));
        
        log.debug("The input {} is derived to: {}", value, derivedValue);
        return derivedValue;
    }

}
