package de.schaack.ml.basics.functions.evaluations.classification.binary;

/**
 * Represents a binary classification prediction with associated truth and
 * predicted values, along with its evaluation (true positive, false
 * positive, etc.).
 */
public class BinaryClassificationPrediction {
    private final boolean groundTruthLabel;
    private final boolean predictionLabel;
    private final BinaryEvaluationEnum evaluation;

    /**
     * Constructs a new {@code BinaryClassificationPrediction} instance with the
     * provided truth status, predicted value.
     * The evaluation is calculated as part of object construction to
     * determine if it's true positive (TP), false positive (FP), etc., based on
     * prediction label against ground truth labels.
     * 
     * @param groundTruthLabel Whether the actual label matches the given instance's
     *                         expected classification outcome. Default: `false`.
     * @param predictionLabel  The predicted binary value from a model for an input
     *                         instance. Should be either 'true' or 'false'.
     *                         Default:
     *                         `false`.
     */
    public BinaryClassificationPrediction(boolean groundTruthLabel, boolean predictionLabel) {
        this.groundTruthLabel = groundTruthLabel;
        this.predictionLabel = predictionLabel;
        this.evaluation = this.updateEvaluation();
    }

    /**
     * Private helper that updates the instance's `evaluation` field with a specific
     * binary classification outcome (true positive, false negative etc.).
     * 
     * @hidden Prediction = true, Ground truth = true -> True Positive TP case.
     * @hidden Prediction = false, Ground truth = false -> True Negative TN case.
     * @hidden Prediction = true, Ground truth = false -> False Positive FP case.
     * @hidden Prediction = false, Ground truth = true -> False Negative FN case.
     */
    private BinaryEvaluationEnum updateEvaluation() {

        if (predictionLabel && groundTruthLabel)
            return BinaryEvaluationEnum.TRUE_POSITIVE;

        else if (!predictionLabel && !groundTruthLabel)
            return BinaryEvaluationEnum.TRUE_NEGATIVE;

        else if (predictionLabel && !groundTruthLabel)
            return BinaryEvaluationEnum.FALSE_POSITIVE;

        else
            return BinaryEvaluationEnum.FALSE_NEGATIVE;
    }

    public BinaryEvaluationEnum getEvaluation() {
        return this.evaluation;
    }

    @Override
    public String toString() {
        return "BinaryClassificationPrediction: [" +
                "groundTruthLabel=" + groundTruthLabel + ", " +
                "predictionLabel=" + predictionLabel + ", " +
                "evaluation=" + evaluation + "]";
    }

}
