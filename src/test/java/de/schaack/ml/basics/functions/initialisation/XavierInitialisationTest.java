package de.schaack.ml.basics.functions.initialisation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import de.schaack.ml.basics.functions.initialisation.implementation.XavierInitialisation;

class XavierInitialisationTest {

    private XavierInitialisation initFunction;

    @BeforeEach
    void setUp() {
        initFunction = new XavierInitialisation();
    }

    @Test
    void testInitializeWeights_HappyPath() {
        int size = 10;
        double[] weights = initFunction.initializeWeights(size);
        assertNotNull(weights);
        assertEquals(size, weights.length);
        for (double weight : weights) {
            assertTrue(weight >= -1 && weight <= 1);
        }
    }

    @Test
    void testInitializeWeights_SizeOne() {
        int size = 1;
        double[] weights = initFunction.initializeWeights(size);
        assertNotNull(weights);
        assertEquals(size, weights.length);
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