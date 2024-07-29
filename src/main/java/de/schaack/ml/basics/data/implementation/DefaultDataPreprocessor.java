package de.schaack.ml.basics.data.implementation;

import de.schaack.ml.basics.data.interfaces.DataSet;

import java.util.stream.IntStream;

import de.schaack.ml.basics.data.interfaces.DataColumn;
import de.schaack.ml.basics.data.interfaces.DataPoint;
import de.schaack.ml.basics.data.interfaces.DataPreprocessor;

public class DefaultDataPreprocessor implements DataPreprocessor {

    private DefaultStatistics[] statistics;
    private double[] means;
    private double[] stds;
    private boolean isInitialised = false;

    public DefaultDataPreprocessor() {

    }

    public <T extends DataSet<? extends DataPoint>> DefaultDataPreprocessor(T dataSet) {
        fitToDataSet(dataSet);
    }

    public <T extends DataSet<? extends DataPoint>> void fitToDataSet(T dataSet) {
        if (isInitialised)
            throw new IllegalArgumentException("Preprocessor is already initialised");

        statistics = new DefaultStatistics[dataSet.getNumberOfFeatures()];
        means = new double[dataSet.getNumberOfFeatures()];
        stds = new double[dataSet.getNumberOfFeatures()];
        IntStream.range(0, dataSet.getNumberOfFeatures())
                .parallel()
                .forEach(i -> preprocess(dataSet.getColumn(i), i));
        isInitialised = true;
    }

    private <T extends DataColumn> void preprocess(T dataColumn, int index) {
        statistics[index] = new DefaultStatistics(dataColumn.getFeatures());
        means[index] = statistics[index].getMean();
        stds[index] = statistics[index].getStandardDeviation();
        IntStream.range(0, dataColumn.getFeatures().length)
                .parallel()
                .forEach(i -> dataColumn.getFeatures()[i] = normalise(dataColumn.getFeature(index), means[index], stds[index]));
    }

    @Override
    public <T extends DataSet<? extends DataPoint>> void preprocess(T dataSet) {
        if (!isInitialised)
            throw new IllegalStateException("Statistics are not initialiased for Preprocessing");

        IntStream.range(0, dataSet.getNumberOfDataPoints())
                .parallel()
                .forEach(i -> preprocessDatapoint(dataSet.getDataPoint(i)));
    }

    @Override
    public <T extends DataPoint> void preprocessDatapoint(T dataPoint) {
        if (!isInitialised)
            throw new IllegalStateException("Statistics are not initialiased for Preprocessing");

        IntStream.range(0, dataPoint.getEntries().length)
                .parallel()
                .forEach(i -> dataPoint.getEntries()[i] = normalise(dataPoint.getEntry(i), means[i], stds[i]));
    }

    public boolean isInitialised() {
        return isInitialised;
    }

    public double normalise(double feature, double mean, double std) {
        return std != 0 ? ((feature - mean) / std) : 0;
    }

    public DefaultStatistics[] getStatistics() {
        return statistics;
    }

    public double[] getMeans() {
        return means;
    }

    public double[] getStds() {
        return stds;
    }

}
