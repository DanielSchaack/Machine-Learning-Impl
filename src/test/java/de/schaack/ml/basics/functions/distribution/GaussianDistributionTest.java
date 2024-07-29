package de.schaack.ml.basics.functions.distribution;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Random;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GaussianDistributionTest {

    @BeforeEach
    void setUp() {
        // Set the seed for reproducibility
        setRandomSeed(42);
    }


    private void setRandomSeed(long seed) {
        Random random = new Random(seed);
        try {
            java.lang.reflect.Field field = GaussianDistribution.class.getDeclaredField("random");
            field.setAccessible(true);
            field.set(null, random);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void testGenerateGaussian_MeanAndStandardDeviation() {
        double mean = 0.0;
        double standardDeviation = 1.0;
        double value = GaussianDistribution.generateGaussian(mean, standardDeviation);
        // Check if within reasonable range, due to randomness
        assertTrue(value > mean - 3 * standardDeviation && value < mean + 3 * standardDeviation);
    }

    @Test
    void testGenerateGaussian_ZeroStandardDeviation() {
        double mean = 0.0;
        double standardDeviation = 0.0;
        double value = GaussianDistribution.generateGaussian(mean, standardDeviation);
        assertEquals(mean, value, 1e-9);
    }

    @Test
    void testGenerateGaussian_NegativeStandardDeviation() {
        double mean = 0.0;
        double standardDeviation = -1.0;
        assertThrows(IllegalArgumentException.class,
                () -> GaussianDistribution.generateGaussian(mean, standardDeviation));
    }

    @Test
    void testGenerateCoordinates_MeanAndStandardDeviation() {
        double meanX = 0.0;
        double meanY = 0.0;
        double standardDeviation = 1.0;
        double[] coordinates = GaussianDistribution.generateCoordinates(meanX, meanY, standardDeviation);
        assertNotNull(coordinates);
        assertEquals(2, coordinates.length);
        // Check each coordinate within reasonable range
        assertTrue(coordinates[0] > meanX - 3 * standardDeviation && coordinates[0] < meanX + 3 * standardDeviation);
        assertTrue(coordinates[1] > meanY - 3 * standardDeviation && coordinates[1] < meanY + 3 * standardDeviation);
    }

    @Test
    void testGenerateCoordinates_ZeroStandardDeviation() {
        double meanX = 0.0;
        double meanY = 0.0;
        double standardDeviation = 0.0;
        double[] coordinates = GaussianDistribution.generateCoordinates(meanX, meanY, standardDeviation);
        assertNotNull(coordinates);
        assertEquals(2, coordinates.length);
        assertEquals(meanX, coordinates[0], 1e-9);
        assertEquals(meanY, coordinates[1], 1e-9);
    }

    @Test
    void testGenerateCoordinates_NegativeStandardDeviation() {
        double meanX = 0.0;
        double meanY = 0.0;
        double standardDeviation = -1.0;
        assertThrows(IllegalArgumentException.class,
                () -> GaussianDistribution.generateCoordinates(meanX, meanY, standardDeviation));
    }

}
