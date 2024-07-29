package de.schaack.ml.basics.data.implementation;

import java.util.Arrays;

import de.schaack.ml.basics.data.interfaces.Statistics;

/**
 * A default implementation of the {@link Statistics} interface.
 */
public class DefaultStatistics implements Statistics {

    private double[] features;
    private int count;
    private double min = Double.MAX_VALUE;
    private double max = Double.MIN_VALUE;
    private double sum;

    /**
     * Constructs a {@code DefaultStatistics} object with the specified features.
     *
     * @param features an array of double values representing the features.
     */
    public DefaultStatistics(double[] features) {
        this.features = features;
        this.count = features.length;
        Arrays.sort(this.features);

        for (double value : features) {
            if (value < min) {
                min = value;
            }
            if (value > max) {
                max = value;
            }
            sum += value;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getCount() {
        return count;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getMean() {
        return sum / count;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getStandardDeviation() {
        double mean = getMean();
        double temp = 0;
        for (double value : features) {
            temp += Math.pow(value - mean, 2);
        }
        return Math.sqrt(temp / count);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getMin() {
        return min;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getMax() {
        return max;
    }

    /**
     * {@inheritDoc}
     *
     * @throws IllegalArgumentException if the percentile is out of range or if there are no features.
     */
    @Override
    public double getPercentile(double percents) {
        if (count == 0 || percents < 0 || percents > 100) {
            throw new IllegalArgumentException("Invalid input");
        }
        int position = (int)Math.floor((percents / 100.0 * (count - 1)));
        return features[Math.min(position, count - 1)];
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double[] getPercentiles(double... percents) {
        double[] result = new double[percents.length];
        for (int i = 0; i < percents.length; i++) {
            result[i] = getPercentile(percents[i]);
        }
        return result;
    }

    /**
     * Returns a string representation of the {@code DefaultStatistics}.
     *
     * @return a string representation of the {@code DefaultStatistics}, including mean, standard deviation, min, max, and percentiles.
     */
    @Override
    public String toString() {
        double[] percentiles = getPercentiles(25.0, 50.0, 75.0);
        return "\tmean=" + getMean() +
               "\tstandardDeviation=" + getStandardDeviation() +
               "\tmin=" + getMin() +
               "\tmax=" + getMax() +
               "\tpercentile25th=" + percentiles[0] +
               "\tpercentile50th=" + percentiles[1] +
               "\tpercentile75th=" + percentiles[2];
    }
}
