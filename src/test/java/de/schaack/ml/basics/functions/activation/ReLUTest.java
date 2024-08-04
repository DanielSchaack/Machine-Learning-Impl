package de.schaack.ml.basics.functions.activation;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import de.schaack.ml.basics.functions.activation.implementation.ReLU;

class ReLUTest {

    private ReLU relu;

    @BeforeEach
    void setUp() {
        relu = new ReLU();
    }

    @Test
    void testActivate_PositiveValue() {
        double value = 2.0;
        double result = relu.activate(value);
        assertEquals(value, result, 1e-9);
    }

    @Test
    void testActivate_ZeroValue() {
        double value = 0.0;
        double result = relu.activate(value);
        assertEquals(0.0, result, 1e-9);
    }

    @Test
    void testActivate_NegativeValue() {
        double value = -1.0;
        double result = relu.activate(value);
        assertEquals(0.0, result, 1e-9);
    }

    @Test
    void testDeriveActivation_PositiveValue() {
        double value = 2.0;
        relu.activate(value);
        double result = relu.deriveActivation(value);
        assertEquals(1.0, result, 1e-9);
    }

    @Test
    void testDeriveActivation_ZeroValue() {
        double value = 0.0;
        relu.activate(value);
        double result = relu.deriveActivation(value);
        assertEquals(0.0, result, 1e-9);
    }

    @Test
    void testDeriveActivation_NegativeValue() {
        double value = -1.0;
        relu.activate(value);
        double result = relu.deriveActivation(value);
        assertEquals(0.0, result, 1e-9);
    }
}
