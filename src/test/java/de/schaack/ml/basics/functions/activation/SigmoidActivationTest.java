package de.schaack.ml.basics.functions.activation;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import de.schaack.ml.basics.functions.activation.implementation.SigmoidActivation;

class SigmoidActivationTest {

    private SigmoidActivation sigmoid;

    @BeforeEach
    void setUp() {
        sigmoid = new SigmoidActivation();
    }

    @Test
    void testActivate_PositiveValue() {
        double value = 1.0;
        double result = sigmoid.activate(value);
        double expected = 1.0 / (1.0 + Math.exp(-value));
        assertEquals(expected, result, 1e-9);
    }

    @Test
    void testActivate_ZeroValue() {
        double value = 0.0;
        double result = sigmoid.activate(value);
        double expected = 1.0 / (1.0 + Math.exp(-value));
        assertEquals(expected, result, 1e-9);
    }

    @Test
    void testActivate_NegativeValue() {
        double value = -1.0;
        double result = sigmoid.activate(value);
        double expected = 1.0 / (1.0 + Math.exp(-value));
        assertEquals(expected, result, 1e-9);
    }

    @Test
    void testDeriveActivation_PositiveValue() {
        double value = 1.0;
        double activatedValue = sigmoid.activate(value);
        double result = sigmoid.deriveActivation(value);
        double expected = activatedValue * (1 - activatedValue);
        assertEquals(expected, result, 1e-9);
    }

    @Test
    void testDeriveActivation_ZeroValue() {
        double value = 0.0;
        double activatedValue = sigmoid.activate(value);
        double result = sigmoid.deriveActivation(value);
        double expected = activatedValue * (1 - activatedValue);
        assertEquals(expected, result, 1e-9);
    }

    @Test
    void testDeriveActivation_NegativeValue() {
        double value = -1.0;
        double activatedValue = sigmoid.activate(value);
        double result = sigmoid.deriveActivation(value);
        double expected = activatedValue * (1 - activatedValue);
        assertEquals(expected, result, 1e-9);
    }

}