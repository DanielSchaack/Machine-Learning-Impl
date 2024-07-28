package de.schaack.ml.basics.functions.loss.interfaces;

public interface LossFunction {
    double deriveLoss(double trueLabel, double predictedLabel);

    double calculateLoss(double trueLabels, double predictedLabels);

    double calculateLoss(double[] trueLabels, double[] predictedLabels);
}
