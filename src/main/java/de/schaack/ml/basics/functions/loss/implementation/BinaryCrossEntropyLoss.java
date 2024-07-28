package de.schaack.ml.basics.functions.loss.implementation;

import java.util.stream.IntStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.schaack.ml.basics.functions.loss.interfaces.LossFunction;

public class BinaryCrossEntropyLoss implements LossFunction {

    
    private static final Logger log = LoggerFactory.getLogger(BinaryCrossEntropyLoss.class);

    @Override
    public double calculateLoss(double trueLabel, double predictedLabel) throws IllegalArgumentException {
        double loss = (-trueLabel * Math.log(predictedLabel)) - (1 - trueLabel * Math.log(1 - predictedLabel));
        log.debug("The loss of trueLabel {} with perdictedLabel {} is calculated as {}", trueLabel, predictedLabel, loss);
        return loss ;
    }

    @Override
    public double deriveLoss(double trueLabel, double predictedLabel) {
        double derivedValue = - ((trueLabel/predictedLabel) - ((1-trueLabel)/(1-predictedLabel)));
        log.debug("The derived loss of trueLabel {} with perdictedLabel {} is calculated as {}", trueLabel, predictedLabel, derivedValue);
        return derivedValue;
    }

    @Override
    public double calculateLoss(double[] trueLabels, double[] predictedLabels) throws IllegalArgumentException {
        if (trueLabels.length != predictedLabels.length)
            throw new IllegalArgumentException("The inputs do not have the same length");

        return (1.0 / trueLabels.length) * IntStream.range(0, trueLabels.length)
                .parallel()
                .mapToDouble(i -> calculateLoss(trueLabels[i], predictedLabels[i]))
                .sum();
    }
}
