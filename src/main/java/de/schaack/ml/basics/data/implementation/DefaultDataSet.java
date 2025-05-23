package de.schaack.ml.basics.data.implementation;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.Random;

import de.schaack.ml.basics.data.interfaces.DataColumn;
import de.schaack.ml.basics.data.interfaces.DataPoint;
import de.schaack.ml.basics.data.interfaces.DataSet;

/**
 * A default implementation of the {@link DataSet} interface.
 */
public class DefaultDataSet implements DataSet<DataPoint> {

    private final String[] attributeNames;
    private final String[] attributeDescriptions;
    private final DataPoint[] dataPoints;
    private boolean hasLabels;
    private final int numberOfFeatures;
    private final int numberOfDataPoints;

    /**
     * Constructs a {@code DefaultDataSet} with the specified data points and no
     * attribute metadata or labels.
     *
     * @param dataPoints a collection of {@link DataPoint} objects.
     */
    public DefaultDataSet(Collection<? extends DataPoint> dataPoints) {
        this(dataPoints, null, null, false);
    }

    /**
     * Constructs a {@code DefaultDataSet} with the specified data points and no
     * attribute metadata or labels.
     *
     * @param dataPoints a collection of {@link DataPoint} objects.
     * @param hasLabels  a boolean flag indicating whether the dataset contains
     *                   labels.
     */
    public DefaultDataSet(Collection<? extends DataPoint> dataPoints, boolean hasLabel) {
        this(dataPoints, null, null, hasLabel);
    }

    /**
     * Constructs a {@code DefaultDataSet} with the specified data points and
     * attribute names.
     *
     * @param dataPoints     a collection of {@link DataPoint} objects.
     * @param attributeNames a collection of attribute names, which can be
     *                       {@code null}.
     * @throws IllegalArgumentException if the input is invalid.
     */
    public DefaultDataSet(Collection<? extends DataPoint> dataPoints, Collection<String> attributeNames)
            throws IllegalArgumentException {
        this(dataPoints, attributeNames, null, false);
    }

    /**
     * Constructs a {@code DefaultDataSet} with the specified data points, attribute
     * names, and label flag.
     *
     * @param dataPoints     a collection of {@link DataPoint} objects.
     * @param attributeNames a collection of attribute names, which can be
     *                       {@code null}.
     * @param hasLabels      a boolean flag indicating whether the dataset contains
     *                       labels.
     * @throws IllegalArgumentException if the input is invalid.
     */
    public DefaultDataSet(Collection<? extends DataPoint> dataPoints, Collection<String> attributeNames,
            boolean hasLabels)
            throws IllegalArgumentException {
        this(dataPoints, attributeNames, null, hasLabels);
    }

    /**
     * Constructs a {@code DefaultDataSet} with the specified data points, attribute
     * names, and attribute descriptions.
     *
     * @param dataPoints            a collection of {@link DataPoint} objects.
     * @param attributeNames        a collection of attribute names, which can be
     *                              {@code null}.
     * @param attributeDescriptions a collection of attribute descriptions, which
     *                              can be {@code null}.
     * @throws IllegalArgumentException if the input is invalid.
     */
    public DefaultDataSet(Collection<? extends DataPoint> dataPoints, Collection<String> attributeNames,
            Collection<String> attributeDescriptions) throws IllegalArgumentException {
        this(dataPoints, attributeNames, attributeDescriptions, false);
    }

    /**
     * Constructs a {@code DefaultDataSet} with the specified data points, attribute
     * names, attribute descriptions, and label flag.
     *
     * @param dataPoints            a collection of {@link DataPoint} objects.
     * @param attributeNames        a collection of attribute names, which can be
     *                              {@code null}.
     * @param attributeDescriptions a collection of attribute descriptions, which
     *                              can be {@code null}.
     * @param hasLabels             a boolean flag indicating whether the dataset
     *                              contains labels.
     * @throws IllegalArgumentException if the input is invalid.
     */
    public DefaultDataSet(Collection<? extends DataPoint> dataPoints, Collection<String> attributeNames,
            Collection<String> attributeDescriptions, boolean hasLabels)
            throws IllegalArgumentException {
        if (dataPoints.isEmpty()) {
            throw new IllegalArgumentException("dataPoints must not be empty.");
        }

        if (!isDataPointSizeUniversal(dataPoints)) {
            throw new IllegalArgumentException("The size of dataPoint.getEntries must be equal for all data points.");
        }

        this.numberOfDataPoints = dataPoints.size();
        this.dataPoints = dataPoints.toArray(new DataPoint[this.numberOfDataPoints]);
        this.numberOfFeatures = this.dataPoints[0].getEntries().length;
        this.hasLabels = hasLabels;

        if (haveAttributesCorrectSize(attributeNames, attributeDescriptions, numberOfFeatures)) {
            throw new IllegalArgumentException(
                    "Invalid input: names must not be null, descriptions length must match names length, and they must all have the same number of entries as the number of features.");
        }

        this.attributeNames = attributeNames != null
                ? attributeNames.toArray(new String[attributeNames.size()])
                : new String[0];

        this.attributeDescriptions = attributeDescriptions != null
                ? attributeDescriptions.toArray(new String[attributeDescriptions.size()])
                : new String[0];
    }

    /**
     * Checks if the given collections are non-null and have matching sizes or match
     * the expected number of features.
     *
     * @param attributeNames        Collection of attribute names (can be null).
     * @param attributeDescriptions Collection of attribute descriptions (can be
     *                              null).
     * @param numberOfFeatures      Expected number of features.
     * @return true if one of the following conditions is met:
     *         <p>
     *         - Both name and description collections are non-null but have
     *         different sizes.
     *         <p>
     *         - The size of the name collection does not match the number of
     *         features.
     *         <p>
     *         - The size of the description collection does not match the number of
     *         features.
     */
    private boolean haveAttributesCorrectSize(Collection<String> attributeNames,
            Collection<String> attributeDescriptions, int numberOfFeatures) {
        return (attributeNames != null && attributeDescriptions != null &&
                attributeDescriptions.size() != attributeNames.size()) ||
                (attributeNames != null && attributeNames.size() != numberOfFeatures) ||
                (attributeDescriptions != null && attributeDescriptions.size() != numberOfFeatures);
    }

    /**
     * Checks if all data points have the same number of features.
     *
     * @param dataPoints a collection of {@link DataPoint} objects.
     * @return <code>true</code> if all data points have the same number of
     *         features; <code>false</code> otherwise.
     */
    public boolean isDataPointSizeUniversal(Collection<? extends DataPoint> dataPoints) {
        int firstDataRowSize = dataPoints.iterator().next().getEntries().length;
        return dataPoints.stream().allMatch(dataPoint -> dataPoint.getEntries().length == firstDataRowSize);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getNumberOfFeatures() {
        return numberOfFeatures;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getNumberOfDataPoints() {
        return numberOfDataPoints;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DataPoint[] getDataPoints() {
        return this.dataPoints;
    }

    /**
     * {@inheritDoc}
     *
     * @throws IndexOutOfBoundsException if the index is out of range.
     */
    @Override
    public DataPoint getDataPoint(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= getNumberOfDataPoints()) {
            throw new IndexOutOfBoundsException("This dataset has not enough data points.");
        }
        return this.dataPoints[index];
    }

    /**
     * {@inheritDoc}
     *
     * @throws IndexOutOfBoundsException if the index is out of range.
     */
    @Override
    public DataColumn getColumn(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= getNumberOfFeatures()) {
            throw new IndexOutOfBoundsException("This dataset has not enough features.");
        }
        double[] features = new double[getNumberOfDataPoints()];
        for (int i = 0; i < getNumberOfDataPoints(); i++) {
            features[i] = dataPoints[i].getEntry(index);
        }
        return new DefaultDataColumn(features);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double[][] getFeatures() {
        double[][] features = new double[getNumberOfDataPoints()][getNumberOfFeatures()];
        for (int i = 0; i < getNumberOfDataPoints(); i++) {
            features[i] = dataPoints[i].getEntries();
        }
        return features;
    }

    @Override
    public boolean hasLabels() {
        return hasLabels;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DefaultDataSet setHasLabels(boolean hasLabels) {
        this.hasLabels = hasLabels;
        return this;
    }

    /**
     * {@inheritDoc}
     *
     * @throws IllegalStateException if the dataset does not contain labels.
     */
    @Override
    public double[] getLabels() throws IllegalStateException {
        if (!hasLabels()) {
            throw new IllegalStateException("This dataset has no labels.");
        }
        double[] labels = new double[getNumberOfDataPoints()];
        for (int i = 0; i < getNumberOfDataPoints(); i++) {
            labels[i] = this.dataPoints[i].getLabel();
        }
        return labels;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean hasAttributeNames() {
        return attributeNames.length > 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String[] attributeNames() {
        return attributeNames;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean hasAttributeDescriptions() {
        return attributeDescriptions.length > 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String[] attributeDescriptions() {
        return attributeDescriptions;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DefaultDataSet subset(Integer[] indices) {
        DataPoint[] subsetDataPoints = new DataPoint[indices.length];
        for (int i = 0; i < indices.length; i++) {
            subsetDataPoints[i] = getDataPoint(indices[i]);
        }
        return new DefaultDataSet(
                Arrays.asList(subsetDataPoints),
                attributeNames.length == 0 ? null : Arrays.asList(attributeNames),
                attributeDescriptions.length == 0 ? null : Arrays.asList(attributeDescriptions),
                hasLabels);
    }

    /**
     * {@inheritDoc}
     *
     * @throws IllegalArgumentException  if the beginning index is not less than the
     *                                   ending index.
     * @throws IndexOutOfBoundsException if the end index is out of range.
     */
    @Override
    public DefaultDataSet subset(int indexBeginning, int indexEnd)
            throws IllegalArgumentException, IndexOutOfBoundsException {
        if (indexEnd < indexBeginning) {
            throw new IllegalArgumentException("The beginning index must be smaller than the ending index.");
        }
        if (indexBeginning < 0 || indexEnd < 0 || indexEnd > getNumberOfDataPoints()) {
            throw new IndexOutOfBoundsException("Indices are out of range.");
        }
        return new DefaultDataSet(
                Arrays.asList(Arrays.copyOfRange(getDataPoints(), indexBeginning, indexEnd)),
                attributeNames.length == 0 ? null : Arrays.asList(attributeNames),
                attributeDescriptions.length == 0 ? null : Arrays.asList(attributeDescriptions),
                hasLabels);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void shuffle(Random random) {
        Collections.shuffle(Arrays.asList(dataPoints), random);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void shuffle() {
        Collections.shuffle(Arrays.asList(dataPoints), new Random());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Iterator<DataPoint> iterator() {
        return Arrays.asList(dataPoints).iterator();
    }
}
