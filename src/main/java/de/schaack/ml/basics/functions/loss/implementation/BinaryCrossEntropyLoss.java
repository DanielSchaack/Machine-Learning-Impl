package de.schaack.ml.basics.functions.loss.implementation;

import java.util.stream.IntStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.schaack.ml.basics.functions.loss.interfaces.LossFunction;

public class BinaryCrossEntropyLoss implements LossFunction {

    private static final Logger log = LoggerFactory.getLogger(BinaryCrossEntropyLoss.class);

    private double currentValue = 0;
    private double currentGradient = 0;

    @Override
    public double calculateLoss(double trueLabel, double predictedLabel) throws IllegalArgumentException {
        if (predictedLabel <= 0.0 || predictedLabel >= 1.0)
            throw new IllegalArgumentException("PredictedLabel must be within 0 and 1: " + predictedLabel);

        currentValue = -(trueLabel * Math.log(predictedLabel)) - (1 - trueLabel * Math.log(1 - predictedLabel));
        log.debug("The loss of trueLabel {} with predictedLabel {} is calculated as {}", trueLabel, predictedLabel,
                currentValue);
        return currentValue;
    }

    @Override
    public double deriveLoss(double trueLabel, double predictedLabel) {
        if (predictedLabel <= 0.0 || predictedLabel >= 1.0)
            throw new IllegalArgumentException("PredictedLabel must be within 0 and 1: " + predictedLabel);

        currentGradient = -((trueLabel / predictedLabel) - ((1 - trueLabel) / (1 - predictedLabel)));
        log.debug("The derived loss of trueLabel {} with predictedLabel {} is calculated as {}", trueLabel,
                predictedLabel, currentGradient);
        return currentGradient;
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
