package de.schaack.ml.basics.functions.initialisation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import de.schaack.ml.basics.functions.initialisation.implementation.ZeroWeightsInitialisation;

class ZeroWeightsInitialisationTest {

    private ZeroWeightsInitialisation initFunction;

    @BeforeEach
    void setUp() {
        initFunction = new ZeroWeightsInitialisation();
    }

    @Test
    void testInitializeWeights_HappyPath() {
        int size = 10;
        double[] weights = initFunction.initializeWeights(size);
        assertNotNull(weights);
        assertEquals(size, weights.length);
        for (double weight : weights) {
            assertEquals(0.0, weight, 1e-9);
        }
    }

    @Test
    void testInitializeWeights_SizeOne() {
        int size = 1;
        double[] weights = initFunction.initializeWeights(size);
        assertNotNull(weights);
        assertEquals(size, weights.length);
        assertEquals(0.0, weights[0], 1e-9);
    }

    @Test
    void testInitializeWeights_InvalidSizeZero() {
        int size = 0;
        assertThrows(IllegalArgumentException.class, () -> initFunction.initializeWeights(size));
    }

    @Test
    void testInitializeWeights_InvalidSizeNegative() {
        int size = -1;
        assertThrows(IllegalArgumentException.class, () -> initFunction.initializeWeights(size));
    }

}