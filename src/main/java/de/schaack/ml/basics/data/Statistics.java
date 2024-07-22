package de.schaack.ml.basics.data;

/**
 * Represents statistical analysis for a set of data features.
 */
public interface Statistics {

    /**
     * Retrieves the count of features.
     *
     * @return the count of features.
     */
    int getCount();

    /**
     * Retrieves the mean (average) value of the features.
     *
     * @return the mean value of the features.
     */
    double getMean();

    /**
     * Retrieves the standard deviation of the features.
     *
     * @return the standard deviation of the features.
     */
    double getStandardDeviation();

    /**
     * Retrieves the minimum value of the features.
     *
     * @return the minimum value of the features.
     */
    double getMin();

    /**
     * Retrieves the maximum value of the features.
     *
     * @return the maximum value of the features.
     */
    double getMax();

    /**
     * Retrieves the value at the specified percentile of the features.
     *
     * @param p the percentile to retrieve (0 to 100).
     * @return the value at the specified percentile.
     * @throws IllegalArgumentException if the percentile is out of range.
     */
    double getPercentile(double p);

    /**
     * Retrieves the values at the specified percentiles of the features.
     *
     * @param p an array of percentiles to retrieve (0 to 100).
     * @return an array of values at the specified percentiles.
     */
    double[] getPercentiles(double... p);
}
