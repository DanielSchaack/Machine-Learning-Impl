package de.schaack.ml.basics.data.interfaces;

/**
 * Represents a data column with features and optional attribute metadata.
 */
public interface DataColumn {

    /**
     * Checks if the data column has an attribute name.
     *
     * @return <code>true</code> if the data column has an attribute name; <code>false</code> otherwise.
     */
    boolean hasAttributeName();

    /**
     * Retrieves the attribute name of the data column.
     *
     * @return the attribute name of the data column.
     */
    String attributeName();

    /**
     * Checks if the data column has an attribute description.
     *
     * @return <code>true</code> if the data column has an attribute description; <code>false</code> otherwise.
     */
    boolean hasAttributeDescription();

    /**
     * Retrieves the attribute description of the data column.
     *
     * @return the attribute description of the data column.
     */
    String attributeDescription();

    /**
     * Retrieves all features of the data column.
     *
     * @return an array of double values representing the features.
     */
    double[] getFeatures();

    /**
     * Retrieves the feature at the specified index.
     *
     * @param index the index of the feature to retrieve.
     * @return the feature at the specified index.
     */
    double getFeature(int index);

    /**
     * Retrieves a subset of features at the specified indices.
     *
     * @param indices an array of indices for the features to retrieve.
     * @return an array of double values representing the subset of features.
     */
    double[] subset(int[] indices);

    /**
     * Retrieves a subset of features from the specified beginning index (inclusive) to the end index (exclusive).
     *
     * @param indexBeginning the beginning index (inclusive).
     * @param indexEnd the end index (exclusive).
     * @return an array of double values representing the subset of features.
     */
    double[] subset(int indexBeginning, int indexEnd);

    /**
     * Retrieves statistics for the data column.
     *
     * @return a {@link Statistics} object containing statistical information about the data column.
     */
    <T extends Statistics> T getStatistics();
}
