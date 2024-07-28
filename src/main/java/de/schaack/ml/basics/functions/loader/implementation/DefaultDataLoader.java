package de.schaack.ml.basics.functions.loader.implementation;

import de.schaack.ml.basics.data.interfaces.DataSet;
import de.schaack.ml.basics.functions.loader.interfaces.DataLoaderInterface;

public class DefaultDataLoader implements DataLoaderInterface {

    private DataSet dataSet;
    private int batchSize = 0;
    private int currentBatch = 0;

    public DefaultDataLoader(int batchSize) {
        setBatchSize(batchSize);
    }

    @Override
    public void setDataToIterate(DataSet dataSet) {
        this.dataSet = dataSet;
    }

    @Override
    public void setBatchSize(int batchSize) {
        this.batchSize = batchSize;
    }

    @Override
    public int getBatchSize() {
        return this.batchSize;
    }

    @Override
    public DataSet getBatch(int batchNumber) {
        currentBatch = batchNumber + 1;
        int upperBatchNumber = (currentBatch * batchSize > dataSet.getNumberOfDataPoints())
                ? dataSet.getNumberOfDataPoints()
                : currentBatch * batchSize;
        return dataSet.subset(batchNumber * batchSize, upperBatchNumber);
    }

    @Override
    public int getBatchNumber() {
        return currentBatch;
    }

    @Override
    public boolean hasNext() {
        return currentBatch * batchSize >= dataSet.getNumberOfFeatures();
    }
}
