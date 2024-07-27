package de.schaack.ml.basics.data.implementation;

import java.util.Arrays;
import java.util.Optional;

import de.schaack.ml.basics.data.interfaces.DataPoint;

/**
 * A default implementation of the {@link DataPoint} interface.
 */
public class DefaultDataPoint implements DataPoint {

    private double[] features;
    private Double label;
    private boolean hasBias = false;

    /**
     * Constructs a {@code DefaultDataPoint} with the specified features and no
     * label.
     *
     * @param features an array of double values representing the features of the
     *                 data point.
     */
    public DefaultDataPoint(double[] features, boolean includeBias) {

        this(features, null, includeBias);
    }

    /**
     * Constructs a {@code DefaultDataPoint} with the specified features and label.
     *
     * @param features an array of double values representing the features of the
     *                 data point.
     * @param label    the label of the data point, which can be {@code null}.
     */
    public DefaultDataPoint(double[] features, Double label, boolean includeBias) {
        this.hasBias = includeBias;
        if (hasBias) {
            this.features = new double[features.length + 1];
            this.features[0] = 1; // Bias term
            System.arraycopy(features, 0, this.features, 1, features.length);
        } else {
            this.features = features;
        }
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
     * {@inheritDoc}
     */
    @Override
    public Optional<Double> getBias() {
        return hasBias() ? Optional.of(getEntries()[0]) : Optional.empty();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double[] getNonBias() {
        int startingIndex = hasBias() ? 1 : 0;
        return Arrays.copyOfRange(getEntries(), startingIndex, getEntries().length);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean hasBias() {
        return this.hasBias;
    }

    @Override
    public String toString() {
        return "DefaultDataPoint: [features=" + Arrays.toString(features) + "]";
    }

}