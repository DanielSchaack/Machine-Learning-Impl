package de.schaack.ml.basics.functions.activation;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import de.schaack.ml.basics.functions.activation.implementation.SigmoidActivation;

class SigmoidActivationTest {
    private SigmoidActivation sigmoidActivation;
    private static final double DELTA = 1e-4; // for double comparisons

    @BeforeEach
    public void setUp() {
        sigmoidActivation = new SigmoidActivation();
    }

    @ParameterizedTest
    @CsvSource({
        "0, 0.5",
        "1, 0.7310585786300049",
        "-1, 0.2689414213699951",
        "10, 0.9999546021312976",
        "-10, 4.539786870243442E-5"
    })
    public void testActivate(double input, double expected) {
        assertEquals(expected, sigmoidActivation.activate(input), DELTA);
    }

    @Test
    public void testActivateExtremePositive() {
        assertEquals(1.0, sigmoidActivation.activate(1000), DELTA);
    }

    @Test
    public void testActivateExtremeNegative() {
        assertEquals(0.0, sigmoidActivation.activate(-1000), DELTA);
    }

    @ParameterizedTest
    @CsvSource({
        "0, 1, 0.25",
        "1, 1, 0.19661193324148185",
        "-1, 1, 0.19661193324148185"
    })
    public void testDeriveActivation(double input, double globalDerivative, double expected) {
        sigmoidActivation.activate(input);  // Activate with the input value
        assertEquals(expected, sigmoidActivation.deriveActivation(globalDerivative), DELTA);
    }

    @Test
    public void testGetCurrentGradient() {
        sigmoidActivation.activate(0);
        sigmoidActivation.deriveActivation(1);
        assertEquals(0.25, sigmoidActivation.getCurrentGradient(), DELTA);
    }

    @ParameterizedTest
    @CsvSource({
        "5, -10, 10, 5",
        "-15, -10, 10, -10",
        "15, -10, 10, 10",
        "0, -10, 10, 0"
    })
    public void testBoxBetween(double value, double minValue, double maxValue, double expected) {
        assertEquals(expected, SigmoidActivation.boxBetween(value, minValue, maxValue), DELTA);
    }

}