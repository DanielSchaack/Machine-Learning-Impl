package de.schaack.ml.basics.functions.initialisation.implementation;

import de.schaack.ml.basics.functions.initialisation.interfaces.InitialisationFunction;

public class ZeroWeightsInitialisation implements InitialisationFunction {
    @Override
    public double[] initializeWeights(int size) {
        return new double[size];
    }
}
