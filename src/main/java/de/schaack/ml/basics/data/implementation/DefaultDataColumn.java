package de.schaack.ml.basics.data.implementation;

import java.util.Arrays;

import de.schaack.ml.basics.data.interfaces.DataColumn;
import de.schaack.ml.basics.data.interfaces.Statistics;

/**
 * A default implementation of the {@link DataColumn} interface.
 */
public class DefaultDataColumn implements DataColumn {

    private String attributeName;
    private String attributeDescription;
    private double[] features;

    /**
     * Constructs a {@code DefaultDataColumn} with the specified features and no
     * attribute metadata.
     *
     * @param features an array of double values representing the features of the
     *                 data column.
     */
    public DefaultDataColumn(double[] features) {
        this(features, null, null);
    }

    /**
     * Constructs a {@code DefaultDataColumn} with the specified features and
     * attribute name.
     *
     * @param features      an array of double values representing the features of
     *                      the data column.
     * @param attributeName the name of the attribute, which can be {@code null}.
     */
    public DefaultDataColumn(double[] features, String attributeName) {
        this(features, attributeName, null);
    }

    /**
     * Constructs a {@code DefaultDataColumn} with the specified features, attribute
     * name, and attribute description.
     *
     * @param features             an array of double values representing the
     *                             features of the data column.
     * @param attributeName        the name of the attribute, which can be
     *                             {@code null}.
     * @param attributeDescription the description of the attribute, which can be
     *                             {@code null}.
     */
    public DefaultDataColumn(double[] features, String attributeName, String attributeDescription) {
        this.features = features;
        this.attributeName = attributeName;
        this.attributeDescription = attributeDescription;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean hasAttributeName() {
        return attributeName != null && !attributeName.isEmpty();
    }

    /**
     * {@inheritDoc}
     *
     * @throws IllegalStateException if the data column has no attribute name.
     */
    @Override
    public String attributeName() throws IllegalStateException {
        if (!hasAttributeName()) {
            throw new IllegalStateException("This data column has no attribute name");
        }
        return attributeName;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean hasAttributeDescription() {
        return attributeDescription != null && !attributeDescription.isEmpty();
    }

    /**
     * {@inheritDoc}
     *
     * @throws IllegalStateException if the data column has no attribute
     *                               description.
     */
    @Override
    public String attributeDescription() throws IllegalStateException {
        if (!hasAttributeDescription()) {
            throw new IllegalStateException("This data column has no attribute description");
        }
        return attributeDescription;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double[] getFeatures() {
        return features;
    }

    /**
     * {@inheritDoc}
     *
     * @throws IndexOutOfBoundsException if the index is out of range.
     */
    @Override
    public double getFeature(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= getFeatures().length) {
            throw new IndexOutOfBoundsException("This data column has not enough features");
        }
        return getFeatures()[index];
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double[] subset(int[] indices) {
        double[] subsetFeatures = new double[indices.length];
        for (int i = 0; i < indices.length; i++) {
            subsetFeatures[i] = getFeature(indices[i]);
        }
        return subsetFeatures;
    }

    /**
     * {@inheritDoc}
     *
     * @throws IllegalArgumentException  if the beginning index is not less than the
     *                                   ending index.
     * @throws IndexOutOfBoundsException if the end index is out of range.
     */
    @Override
    public double[] subset(int indexBeginning, int indexEnd)
            throws IllegalArgumentException, IndexOutOfBoundsException {
        if (indexEnd <= indexBeginning) {
            throw new IllegalArgumentException("The beginning index must be smaller than the ending index");
        }
        if (indexBeginning < 0 || indexEnd < 0 || indexEnd >= getFeatures().length) {
            throw new IndexOutOfBoundsException("This data column has not enough entries");
        }
        return Arrays.copyOfRange(getFeatures(), indexBeginning, indexEnd);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Statistics getStatistics() {
        return new DefaultStatistics(features);
    }
}
