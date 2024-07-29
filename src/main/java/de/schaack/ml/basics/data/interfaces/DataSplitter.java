package de.schaack.ml.basics.data.interfaces;

public interface DataSplitter {

    <T extends DataSet<? extends DataPoint>> T[] split(T dataSet, int testSizePercentage);

    <T extends DataSet<? extends DataPoint>> T[] split(T dataSet, int testSizePercentage, int validationSizePercentage);
}