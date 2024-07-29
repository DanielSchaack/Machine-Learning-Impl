package de.schaack.ml.basics.functions.math;

import java.util.stream.IntStream;

import de.schaack.ml.basics.data.interfaces.DataPoint;

public class MatrixOperations {

    private MatrixOperations() {
    }

    public static double valuesMulData(double[] values, DataPoint dataPoint) {
        if (values.length != dataPoint.getEntries().length) {
            throw new IllegalArgumentException("Weights and dataPoint are not of equal size.");
        }
        return IntStream.range(0, values.length)
                .mapToDouble(i -> values[i] * dataPoint.getEntry(i))
                .sum();
    }

    public static double[] valuesAddValue(double[] values, double value) {
        return IntStream.range(0, values.length)
                .mapToDouble(i -> values[i] + value)
                .toArray();
    }

    public static double[] valuesSubValue(double[] values, double value) {
        return IntStream.range(0, values.length)
                .mapToDouble(i -> values[i] - value)
                .toArray();
    }

    public static double[] valuesMulValue(double[] values, double value) {
        return IntStream.range(0, values.length)
                .mapToDouble(i -> values[i] * value)
                .toArray();
    }

    public static double[] valuesDivValue(double[] values, double value) {
        if (value == 0.0) {
            throw new IllegalArgumentException("Value must no be zero.");
        }
        return IntStream.range(0, values.length)
                .mapToDouble(i -> values[i] * value)
                .toArray();
    }
}
