package de.schaack.ml.basics.data.interfaces;

public interface DataPreprocessor {
    <T extends DataSet<? extends DataPoint>> void fitToDataSet(T dataSet);
    <T extends DataSet<? extends DataPoint>> void preprocess(T dataSet);
    <T extends DataPoint> void preprocessDatapoint(T dataPoint);
}
