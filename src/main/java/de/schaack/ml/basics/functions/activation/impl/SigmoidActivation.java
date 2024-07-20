package de.schaack.ml.basics.functions.activation.impl;

import de.schaack.ml.basics.functions.activation.ActivationFunction;

public class SigmoidActivation implements ActivationFunction {

    @Override
    public double activate(double x) {
        return 1.0 / (1.0 + Math.exp(-x));
    }
    
}
