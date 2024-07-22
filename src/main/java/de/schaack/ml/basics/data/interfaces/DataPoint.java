package de.schaack.ml.basics.data.interfaces;

/**
 * Represents a data point with features and an optional label.
 */
public interface DataPoint {

    /**
     * Retrieves all feature entries of the data point.
     *
     * @return an array of double values representing the feature entries.
     */
    double[] getEntries();

    /**
     * Retrieves the feature entry at the specified index.
     *
     * @param index the index of the feature entry to retrieve.
     * @return the feature entry at the specified index.
     */
    double getEntry(int index);

    /**
     * Retrieves the label of the data point.
     *
     * @return the label of the data point.
     */
    double getLabel();

    /**
     * Checks if the data point has a label.
     *
     * @return <code>true</code> if the data point has a label; <code>false</code> otherwise.
     */
    boolean hasLabel();
}
