package de.schaack.ml.basics.functions.loss;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import de.schaack.ml.basics.functions.loss.implementation.BinaryCrossEntropyLoss;

class BinaryCrossEntropyLossTest {
    private final BinaryCrossEntropyLoss lossFunction = new BinaryCrossEntropyLoss();

    @Test
    void testCalculateLoss_HappyPath() {
        double trueLabel = 1.0;
        double predictedLabel = 0.9;
        double expectedLoss = -(trueLabel * Math.log(predictedLabel)) - (1 - trueLabel * Math.log(1 - predictedLabel));
        double actualLoss = lossFunction.calculateLoss(trueLabel, predictedLabel);
        assertEquals(expectedLoss, actualLoss, 1e-9);
    }

    @Test
    void testCalculateLoss_TrueLabelZero() {
        double trueLabel = 0.0;
        double predictedLabel = 0.1;
        double expectedLoss = -(trueLabel * Math.log(predictedLabel)) - (1 - trueLabel * Math.log(1 - predictedLabel));
        double actualLoss = lossFunction.calculateLoss(trueLabel, predictedLabel);
        assertEquals(expectedLoss, actualLoss, 1e-9);
    }

    @Test
    void testCalculateLoss_HappyPath_PredictedLabel_Half() {
        double trueLabel = 0.0;
        double predictedLabel = 0.5;
        double expectedLoss = -(trueLabel * Math.log(predictedLabel)) - (1 - trueLabel * Math.log(1 - predictedLabel));
        double actualLoss = lossFunction.calculateLoss(trueLabel, predictedLabel);
        assertEquals(expectedLoss, actualLoss, 1e-9);
    }

    @Test
    void testCalculateLoss_InvalidPredictedLabelTooHigh() {
        double trueLabel = 1.0;
        double predictedLabel = 1.1;
        assertThrows(IllegalArgumentException.class, () -> lossFunction.calculateLoss(trueLabel, predictedLabel));
    }

    @Test
    void testCalculateLoss_InvalidPredictedLabelTooLow() {
        double trueLabel = 1.0;
        double predictedLabel = -0.1;
        assertThrows(IllegalArgumentException.class, () -> lossFunction.calculateLoss(trueLabel, predictedLabel));
    }

    @Test
    void testDeriveLoss_HappyPath() {
        double trueLabel = 1.0;
        double predictedLabel = 0.9;
        double expectedDerivedValue = -((trueLabel / predictedLabel) - ((1 - trueLabel) / (1 - predictedLabel)));
        double actualDerivedValue = lossFunction.deriveLoss(trueLabel, predictedLabel);
        assertEquals(expectedDerivedValue, actualDerivedValue, 1e-9);
    }

    @Test
    void testDeriveLoss_TrueLabelZero() {
        double trueLabel = 0.0;
        double predictedLabel = 0.1;
        double expectedDerivedValue = -((trueLabel / predictedLabel) - ((1 - trueLabel) / (1 - predictedLabel)));
        double actualDerivedValue = lossFunction.deriveLoss(trueLabel, predictedLabel);
        assertEquals(expectedDerivedValue, actualDerivedValue, 1e-9);
    }

    @Test
    void testDeriveLoss_PredictedLabel_Halfo() {
        double trueLabel = 0.0;
        double predictedLabel = 0.5;
        double expectedDerivedValue = -((trueLabel / predictedLabel) - ((1 - trueLabel) / (1 - predictedLabel)));
        double actualDerivedValue = lossFunction.deriveLoss(trueLabel, predictedLabel);
        assertEquals(expectedDerivedValue, actualDerivedValue, 1e-9);
    }

    @Test
    void testDeriveLoss_InvalidPredictedLabelTooHigh() {
        double trueLabel = 1.0;
        double predictedLabel = 1.1;
        assertThrows(IllegalArgumentException.class, () -> lossFunction.deriveLoss(trueLabel, predictedLabel));
    }

    @Test
    void testDeriveLoss_InvalidPredictedLabelTooLow() {
        double trueLabel = 1.0;
        double predictedLabel = -0.1;
        assertThrows(IllegalArgumentException.class, () -> lossFunction.deriveLoss(trueLabel, predictedLabel));
    }
}
