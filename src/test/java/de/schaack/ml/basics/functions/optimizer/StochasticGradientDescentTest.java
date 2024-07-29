package de.schaack.ml.basics.functions.optimizer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import de.schaack.ml.basics.functions.optimizer.implementation.StochasticGradientDescent;
import de.schaack.ml.basics.settings.optimiser.implementation.LearningRateOptimiser;

class StochasticGradientDescentTest {

    private StochasticGradientDescent sgd;
    private LearningRateOptimiser mockLearningRateOptimiser;

    @BeforeEach
    void setUp() {
        mockLearningRateOptimiser = new LearningRateOptimiser(0.01);
        sgd = new StochasticGradientDescent(mockLearningRateOptimiser);
    }

    @Test
    void testDefaultConstructor() {
        StochasticGradientDescent sgdDefault = new StochasticGradientDescent();
        assertNotNull(sgdDefault.getSettings());
    }

    @Test
    void testConstructorWithLearningRate() {
        double learningRate = 0.05;
        StochasticGradientDescent sgdWithLR = new StochasticGradientDescent(learningRate);
        assertNotNull(sgdWithLR.getSettings());
        assertEquals(learningRate, sgdWithLR.getSettings().getLearningRate(), 1e-9);
    }

    @Test
    void testConstructorWithSettings() {
        LearningRateOptimiser settings = new LearningRateOptimiser(0.05);
        StochasticGradientDescent sgdWithSettings = new StochasticGradientDescent(settings);
        assertSame(settings, sgdWithSettings.getSettings());
    }

    @Test
    void testCalculateUpdate_PositiveGradient() {
        double valueOld = 5.0;
        int batchSize = 10;
        double gradient = 0.5;
        double newValue = sgd.calculateUpdate(valueOld, batchSize, gradient);
        double expectedUpdate = 0.01 * (1.0 / batchSize) * gradient;
        double expectedValue = valueOld - expectedUpdate;
        assertEquals(expectedValue, newValue, 1e-9);
    }

    @Test
    void testCalculateUpdate_NegativeGradient() {
        double valueOld = 5.0;
        int batchSize = 10;
        double gradient = -0.5;
        double newValue = sgd.calculateUpdate(valueOld, batchSize, gradient);
        double expectedUpdate = 0.01 * (1.0 / batchSize) * gradient;
        double expectedValue = valueOld - expectedUpdate;
        assertEquals(expectedValue, newValue, 1e-9);
    }

    @Test
    void testCalculateUpdate_ZeroGradient() {
        double valueOld = 5.0;
        int batchSize = 10;
        double gradient = 0.0;
        double newValue = sgd.calculateUpdate(valueOld, batchSize, gradient);
        assertEquals(valueOld, newValue, 1e-9);
    }

    @Test
    void testCalculateUpdate_LargeBatchSize() {
        double valueOld = 5.0;
        int batchSize = 1000;
        double gradient = 0.5;
        double newValue = sgd.calculateUpdate(valueOld, batchSize, gradient);
        double expectedUpdate = 0.01 * (1.0 / batchSize) * gradient;
        double expectedValue = valueOld - expectedUpdate;
        assertEquals(expectedValue, newValue, 1e-9);
    }

    @Test
    void testCalculateUpdate_SmallBatchSize() {
        double valueOld = 5.0;
        int batchSize = 1;
        double gradient = 0.5;
        double newValue = sgd.calculateUpdate(valueOld, batchSize, gradient);
        double expectedUpdate = 0.01 * (1.0 / batchSize) * gradient;
        double expectedValue = valueOld - expectedUpdate;
        assertEquals(expectedValue, newValue, 1e-9);
    }

}