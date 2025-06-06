package de.schaack.ml.basics.functions.initialisation.implementation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.schaack.ml.basics.functions.initialisation.interfaces.InitialisationFunction;

public class ZeroWeightsInitialisation implements InitialisationFunction {

    private static final Logger log = LoggerFactory.getLogger(ZeroWeightsInitialisation.class);

    @Override
    public double[] initializeWeights(int size) {
        if (size <= 0)
            throw new IllegalArgumentException("Size must be positive");

        double[] initParameters = new double[size];
        log.debug("Parameters initialised as: {}", initParameters);
        return initParameters;
    }
}
