package de.schaack.ml.basics.functions.activation.implementation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.schaack.ml.basics.functions.activation.interfaces.ActivationFunction;

public class ReLU implements ActivationFunction {

    private static final Logger log = LoggerFactory.getLogger(ReLU.class);

    @Override
    public double activate(double value) {
        double activatedValue = Math.max(0.0, value);
        log.debug("The input {} is activated to: {}", value, activatedValue);
        return activatedValue;
    }

    @Override
    public double deriveActivation(double value) {
        double derivedValue = value > 0 ? 1 : 0;
        log.debug("The input {} is derived to: {}", value, derivedValue);
        return derivedValue > 0 ? 1 : 0;
    }
}