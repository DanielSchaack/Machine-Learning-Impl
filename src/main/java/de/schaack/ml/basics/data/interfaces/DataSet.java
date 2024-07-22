package de.schaack.ml.basics.data.interfaces;

import java.util.Random;

/**
 * Represents a collection of data points and provides various methods to access
 * and manipulate the data.
 */
public interface DataSet extends Iterable<DataPoint> {

    /**
     * Retrieves the number of features in the dataset.
     *
     * @return the number of features.
     */
    int getNumberOfFeatures();

    /**
     * Retrieves the number of data points in the dataset.
     *
     * @return the number of data points.
     */
    int getNumberOfDataPoints();

    /**
     * Retrieves all data points in the dataset.
     *
     * @return an array of {@link DataPoint} objects.
     */
    DataPoint[] getDataPoints();

    /**
     * Retrieves the data point at the specified index.
     *
     * @param index the index of the data point to retrieve.
     * @return the {@link DataPoint} at the specified index.
     */
    DataPoint getDataPoint(int index);

    /**
     * Retrieves the data column at the specified index.
     *
     * @param index the index of the data column to retrieve.
     * @return the {@link DataColumn} at the specified index.
     */
    DataColumn getColumn(int index);

    /**
     * Retrieves all feature values in the dataset.
     *
     * @return a 2D array of double values representing the features.
     */
    double[][] getFeatures();

    /**
     * Checks if the dataset contains labels.
     *
     * @return <code>true</code> if the dataset contains labels; <code>false</code>
     *         otherwise.
     */
    boolean hasLabels();

    /**
     * Retrieves all labels in the dataset.
     *
     * @return an array of double values representing the labels.
     */
    double[] getLabels();

    /**
     * Checks if the dataset contains attribute names.
     *
     * @return <code>true</code> if the dataset contains attribute names;
     *         <code>false</code> otherwise.
     */
    boolean hasAttributeNames();

    /**
     * Retrieves all attribute names in the dataset.
     *
     * @return an array of strings representing the attribute names.
     */
    String[] attributeNames();

    /**
     * Checks if the dataset contains attribute descriptions.
     *
     * @return <code>true</code> if the dataset contains attribute descriptions;
     *         <code>false</code> otherwise.
     */
    boolean hasAttributeDescriptions();

    /**
     * Retrieves all attribute descriptions in the dataset.
     *
     * @return an array of strings representing the attribute descriptions.
     */
    String[] attributeDescriptions();

    /**
     * Retrieves a subset of the dataset at the specified indices.
     *
     * @param indices an array of indices for the data points to retrieve.
     * @return a new {@link DataSet} containing the subset of data points.
     */
    DataSet subset(int[] indices);

    /**
     * Retrieves a subset of the dataset from the specified beginning index
     * (inclusive) to the end index (exclusive).
     *
     * @param indexBeginning the beginning index (inclusive).
     * @param indexEnd       the end index (exclusive).
     * @return a new {@link DataSet} containing the subset of data points.
     */
    DataSet subset(int indexBeginning, int indexEnd);

    /**
     * Shuffles the data points in the dataset using the specified random number
     * generator.
     *
     * @param random the random number generator to use for shuffling.
     */
    void shuffle(Random random);
}
