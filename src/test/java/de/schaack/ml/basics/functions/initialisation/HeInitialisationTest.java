package de.schaack.ml.basics.functions.initialisation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import de.schaack.ml.basics.functions.initialisation.implementation.HeInitialisation;

class HeInitialisationTest {

    private final HeInitialisation heInitialisation = new HeInitialisation();

    @Test
    void testInitializeWeights_ValidSize() {
        int size = 10;
        double[] weights = heInitialisation.initializeWeights(size);
        assertNotNull(weights);
        assertEquals(size, weights.length);
        for (double weight : weights) {
            assertTrue(weight >= -1 && weight <= 1);
        }
    }

    @Test
    void testInitializeWeights_ZeroSize() {
        int size = 0;
        assertThrows(IllegalArgumentException.class, () -> heInitialisation.initializeWeights(size));
    }

    @Test
    void testInitializeWeights_NegativeSize() {
        int size = -5;
        assertThrows(IllegalArgumentException.class, () -> heInitialisation.initializeWeights(size));
    }
}
