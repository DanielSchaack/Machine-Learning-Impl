package de.schaack.ml.basics.data.implementation;

import java.util.Arrays;

import de.schaack.ml.basics.data.interfaces.DataPoint;

/**
 * A default implementation of the {@link DataPoint} interface.
 */
public class DefaultDataPoint implements DataPoint {

    private double[] features;
    private Double label;

    /**
     * Constructs a {@code DefaultDataPoint} with the specified features and no
     * label.
     *
     * @param features an array of double values representing the features of the
     *                 data point.
     */
    public DefaultDataPoint(double[] features) {
        this(features, null);
    }

    /**
     * Constructs a {@code DefaultDataPoint} with the specified features and label.
     *
     * @param features an array of double values representing the features of the
     *                 data point.
     * @param label    the label of the data point, which can be {@code null}.
     */
    public DefaultDataPoint(double[] features, Double label) {
        this.features = features;
        this.label = label;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double[] getEntries() {
        return features;
    }

    /**
     * {@inheritDoc}
     *
     * @throws IndexOutOfBoundsException if the index is out of range.
     */
    @Override
    public double getEntry(int index) throws IndexOutOfBoundsException {
        if (getEntries().length <= index) {
            throw new IndexOutOfBoundsException("This data point has not enough features");
        }
        return getEntries()[index];
    }

    /**
     * {@inheritDoc}
     *
     * @throws IllegalStateException if the data point has no label.
     */
    @Override
    public double getLabel() throws IllegalStateException {
        if (label == null) {
            throw new IllegalStateException("This data point has no label");
        }
        return label;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean hasLabel() {
        return label != null;
    }

    /**
     * Returns a string representation of the {@code DefaultDataPoint}.
     *
     * @return a string representation of the {@code DefaultDataPoint}, including
     *         its features.
     */
    @Override
    public String toString() {
        return "DefaultDataPoint: [features=" + Arrays.toString(features) + "]";
    }
}