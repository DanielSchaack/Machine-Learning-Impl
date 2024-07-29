package de.schaack.ml.basics.functions.initialisation.implementation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.schaack.ml.basics.functions.distribution.GaussianDistribution;
import de.schaack.ml.basics.functions.initialisation.interfaces.InitialisationFunction;

public class HeInitialisation implements InitialisationFunction {

    private static final Logger log = LoggerFactory.getLogger(HeInitialisation.class);

    @Override
    public double[] initializeWeights(int size) {
        if (size <= 0) 
            throw new IllegalArgumentException("Size must be positive");
        

        double[] initParameters = new double[size];
        for (int i = 0; i < initParameters.length; i++) {
            initParameters[i] = GaussianDistribution.generateGaussian(0, 2.0 / size);
        }
        log.debug("Parameters initialised as: {}", initParameters);
        return initParameters;
    
    }
}
