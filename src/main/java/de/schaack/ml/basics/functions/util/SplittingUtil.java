package de.schaack.ml.basics.functions.util;

public class SplittingUtil {
    private SplittingUtil() {

    }

    /**
     * Return indices that can create two subsets from an Array of size
     * amountDataPoints
     * 
     * @param amountDataPoints Array size
     * @param testPercentage   size in percentage of testdata
     * @return inclusive indices for subset boundaries
     */
    public static int[] getIndicesBasedOnPercentage(int amountDataPoints, int testPercentage) {
        if (amountDataPoints == 0 || testPercentage <= 0 || testPercentage > 100)
            throw new IllegalArgumentException(
                    "Invalid input: percentages must be between 0 and 100 and their sum must not exceed 100.");
        double trainDataPercentage = ((100 - testPercentage) / 100.0);
        int splitIndex = (int) Math.ceil(trainDataPercentage * amountDataPoints);
        return new int[] { 0, splitIndex - 1, splitIndex, amountDataPoints - 1 };
    }

    /**
     * Return indices that can create three subsets from an Array of size
     * amountDataPoints
     * 
     * @param amountDataPoints     Array size
     * @param testPercentage       size in percentage of test data
     * @param validationPercentage size in percentage of validation data
     * @return inclusive indices for subset boundaries
     */
    public static int[] getIndicesBasedOnPercentages(int amountDataPoints, int testPercentage,
            int validationPercentage) {
        if (amountDataPoints == 0 || testPercentage < 0 || validationPercentage < 0
                || testPercentage + validationPercentage > 100)
            throw new IllegalArgumentException(
                    "Invalid input: percentages must be between 0 and 100 and their sum must not exceed 100.");

        double trainDataPercentage = ((100 - testPercentage - validationPercentage) / 100.0);
        double trainPlusTestPercentage = ((100 - validationPercentage) / 100.0);
        int targetIndex1 = (int) Math.ceil(trainDataPercentage * amountDataPoints);
        int targetIndex2 = (int) Math.ceil(trainPlusTestPercentage * amountDataPoints);
        return new int[] { 0, targetIndex1 - 1, targetIndex1, targetIndex2 - 1, targetIndex2, amountDataPoints - 1 };
    }
}
