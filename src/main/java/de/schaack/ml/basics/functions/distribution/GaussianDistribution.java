package de.schaack.ml.basics.functions.distribution;

import java.util.Random;

/**
 * The GaussianDistribution class generates Gaussian (normal) distributed random
 * values. It allows generating random values with specified mean and standard
 * deviation, and generating 2D coordinates around specified centers.
 * <p>
 * <p>
 * 
 * The Box-Muller transform is used to convert uniform random numbers into a
 * Gaussian distribution.
 * <p>
 * <p>
 *
 * The Box-Muller transform works as follows:
 * <p>
 * - Generate two independent uniform random numbers U1 and U2 in the range (0,
 * 1).
 * <p>
 * - Calculate a standard normal variable Z using the formulas:
 * <p>
 * Z = sqrt(-2 * ln(U1)) * cos(2 * PI * U2)
 * <p>
 * - This Z is a standard normal variable with mean 0 and standard deviation 1.
 * <p>
 * - Scale and shift Z to the desired mean and standard deviation:
 * <p>
 * result = mean + Z * standardDeviation
 * <p>
 */
public class GaussianDistribution {

    private GaussianDistribution() {
    }

    private static Random random = new Random();

    /**
     * Generates a single Gaussian-distributed random value with specified mean and
     * standard deviation.
     *
     * @param mean              the mean of the Gaussian distribution
     * @param standardDeviation the standard deviation of the Gaussian distribution
     * @return a Gaussian-distributed random value
     */
    public static double generateGaussian(double mean, double standardDeviation) {
        if (standardDeviation < 0)
            throw new IllegalArgumentException("Standard deviation must be non-negative");

        double u1 = random.nextDouble();
        double u2 = random.nextDouble();
        double z = Math.sqrt(-2.0 * Math.log(u1)) * Math.cos(2.0 * Math.PI * u2);
        return mean + z * standardDeviation;
    }

    /**
     * Generates a 2D coordinate (x, y) with each coordinate being a
     * Gaussian-distributed random value.
     *
     * @param meanX             the mean of the Gaussian distribution for the
     *                          x-coordinate
     * @param meanY             the mean of the Gaussian distribution for the
     *                          y-coordinate
     * @param standardDeviation the standard deviation of the Gaussian distribution
     * @return an array containing the generated x and y coordinates
     */
    public static double[] generateCoordinates(double meanX, double meanY, double standardDeviation) {
        double x = generateGaussian(meanX, standardDeviation);
        double y = generateGaussian(meanY, standardDeviation);
        return new double[] { x, y };
    }
}
