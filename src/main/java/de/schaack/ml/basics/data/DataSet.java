package de.schaack.ml.basics.data;

import java.util.Arrays;
import java.util.Collection;

public abstract class DataSet {
    private final String[] labels;
    private final DataRow[] data;
    private final boolean isFinalColumnTruth;

    protected DataSet(Collection<DataRow> dataRows, Collection<String> labels, boolean isFinalColumnTruth) {
        if (dataRows.isEmpty())
            throw new IllegalArgumentException("DataRows must not be empty.");

        if (!isDataRowSizeUniversal(dataRows))
            throw new IllegalArgumentException("The size of dataRow.dataColumns must be equal to the size of labels");
        this.data = dataRows.toArray(new DataRow[dataRows.size()]);
        this.labels = labels.toArray(new String[labels.size()]);
        this.isFinalColumnTruth = isFinalColumnTruth;
    }

    public static boolean isDataRowSizeUniversal(Collection<DataRow> dataRows) {
        int firstDataRowSize = dataRows.iterator().next().getDataColumns().length;
        return dataRows.stream()
                .allMatch(dataRow -> dataRow.getDataColumns().length == firstDataRowSize);
    }

    public String[] getLabels() {
        return this.labels;
    }

    public DataRow[] getData() {
        return this.data;
    }
    
    public DataRow getDataRow(int index) {
        return this.data[index];
    }

    public boolean isFinalColumnTruth() {
        System.getProperty("");
        return this.isFinalColumnTruth;
    }

    @Override
    public String toString() {
        return "DataSet: [" +
                "labels=" + Arrays.toString(labels) + ", " +
                "datasize=" + data.length + ", " +
                "isFinalColumnTruth=" + isFinalColumnTruth + "]";
    }

}
