package de.schaack.ml.basics.functions.evaluations.classification.binary;

import java.util.Collection;
import java.util.EnumMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static de.schaack.ml.basics.functions.evaluations.classification.binary.BinaryEvaluationEnum.*;
import de.schaack.ml.basics.config.BinaryConfusionMatrixConfig;
import lombok.Getter;

@Component
public class BinaryConfusionMatrix {

    @Autowired
    private BinaryConfusionMatrixConfig config;

    EnumMap<BinaryEvaluationEnum, AtomicLong> evaluationMap = new EnumMap<>(BinaryEvaluationEnum.class);

    @Getter
    private Double accuracy = 0.0;
    @Getter
    private Double precision = 0.0;
    @Getter
    private Double recall = 0.0;
    @Getter
    private Double f1 = 0.0;

    public BinaryConfusionMatrix(Collection<BinaryClassificationPrediction> predictions) {
        addEvaluations(predictions);
    }

    public void addEvaluations(Collection<BinaryClassificationPrediction> predictions) {
        StreamSupport.stream(predictions.spliterator(), this.config.isParallel())
                .forEach(prediction -> incrementEvaluation(prediction.getEvaluation()));
        this.setAccuracy(evaluationMap.get(TRUE_POSITIVE).get(), evaluationMap.get(TRUE_NEGATIVE).get(),
                evaluationMap.get(FALSE_POSITIVE).get(), evaluationMap.get(FALSE_NEGATIVE).get());
        this.setPrecision(evaluationMap.get(TRUE_POSITIVE).get(), evaluationMap.get(FALSE_POSITIVE).get());
        this.setRecall(evaluationMap.get(TRUE_POSITIVE).get(), evaluationMap.get(FALSE_NEGATIVE).get());
        this.setF1Score(this.precision, this.recall);
    }

    private void incrementEvaluation(BinaryEvaluationEnum evaluation) {
        evaluationMap.get(evaluation).incrementAndGet();
    }

    /**
     * Sets the accuracy of the model based on true positives, false negatives,
     * and total observations (sum of all classes). Raises an
     * IllegalArgumentException if any observation count is zero.
     * 
     * @param truePositive  Number of correct positive predictions made by a
     *                      classifier.
     * @param trueNegative  Number of correct negative predictions made by a
     *                      classifier.
     * @param falsePositive Number of incorrect positive predictions (type I error).
     * @param falseNegative Number of incorrect negative predictions (type II
     *                      error).
     * @return The current instance for chaining method calls.
     * @throws IllegalArgumentException If the sum of true positives, true
     *                                  negatives,
     *                                  and either false positives or false
     *                                  negatives is zero to avoid division by zero
     *                                  errors in accuracy calculation.
     */
    private BinaryConfusionMatrix setAccuracy(Long truePositive, Long trueNegative, Long falsePositive,
            Long falseNegative)
            throws IllegalArgumentException {
        double denominator = (truePositive + trueNegative + falsePositive + falseNegative);
        // Avoid division by zero error.
        if (denominator == 0.0)
            throw new IllegalArgumentException("The amount of observations must be non-zero to calculate accuracy.");

        double numerator = (truePositive + trueNegative);
        this.accuracy = numerator / denominator;
        return this;
    }

    /**
     * Sets the precision of the model based on true positives and false positives.
     * Raises an
     * IllegalArgumentException if there are no actual positive instances
     * (truePositive + falsePositive == 0).
     * 
     * @param truePositive  Number of correct positive predictions made by a
     *                      classifier.
     * @param falsePositive Number of incorrect negative predictions identified as
     *                      positives (type I error).
     * @return The current instance for chaining method calls.
     * @throws IllegalArgumentException If the sum of actual positives is zero to
     *                                  avoid division by zero errors in precision
     *                                  calculation.
     */
    private BinaryConfusionMatrix setPrecision(Long truePositive, Long falsePositive) throws IllegalArgumentException {
        double denominator = (truePositive + falsePositive);
        // Avoid division by zero error.
        if (denominator == 0.0)
            throw new IllegalArgumentException(
                    "The amount of actual positives must be non-zero to calculate Precision.");

        double numerator = truePositive;
        this.precision = numerator / denominator;
        return this;
    }

    /**
     * Sets the recall (sensitivity or true positive rate) of the model based on
     * true positives and false negatives.
     * Raises an IllegalArgumentException if there are no actual positive instances
     * (truePositive + falseNegative == 0).
     * 
     * @param truePositive  Number of correct positive predictions made by a
     *                      classifier.
     * @param falseNegative Number of incorrect positives identified as negatives
     *                      (type II error).
     * @return The current instance for chaining method calls.
     * @throws IllegalArgumentException If the sum of actual positives is zero to
     *                                  avoid division by zero errors in recall
     *                                  calculation.
     */
    private BinaryConfusionMatrix setRecall(Long truePositive, Long falseNegative) throws IllegalArgumentException {
        double denominator = (truePositive + falseNegative);
        // Avoid division by zero error.
        if (denominator == 0.0)
            throw new IllegalArgumentException("The amount of actual positives must be non-zero to calculate Recall.");

        double numerator = truePositive;
        this.recall = numerator / denominator;
        return this;
    }

    /**
     * Sets the F1 score (harmonic mean of precision and recall). This method
     * calculates
     * both Precision and Recall from their respective methods before calculating
     * the F1 Score. Raises an
     * IllegalArgumentException if either Precision or Recall is zero, as this would
     * lead to division by zero in calculation.
     * 
     * @return The current instance for chaining method calls.
     * @throws IllegalArgumentException If both precision and recall are non-zero
     *                                  (as one of them being zero will make the
     *                                  denominator 0).
     */
    private BinaryConfusionMatrix setF1Score(double precision, double recall) throws IllegalArgumentException {
        double denominator = precision + recall;
        // Avoid division by zero error.
        if (denominator == 0.0)
            throw new IllegalArgumentException("Precision and Recall must be non-zero for F1 calculation.");

        double numerator = 2.0 * precision * recall;
        this.f1 = numerator / denominator;
        return this;
    }
}
