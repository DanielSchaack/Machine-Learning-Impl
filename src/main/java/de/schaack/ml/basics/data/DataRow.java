package de.schaack.ml.basics.data;

import java.util.Arrays;

public abstract class DataRow {
    private final double[] dataColumns;

    protected DataRow(double[] data) {
        this.dataColumns = data;
    }

    public double[] getDataColumns() {
        return dataColumns;
    }

    public double get(int i) {
        return dataColumns[i];
    }

    @Override
    public String toString() {
        return "DataRow: [dataColumns=" + Arrays.toString(dataColumns) + "]";
    }
}
