package de.schaack.ml.basics.functions.loader.implementation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.schaack.ml.basics.data.interfaces.DataPoint;
import de.schaack.ml.basics.data.interfaces.DataSet;
import de.schaack.ml.basics.functions.loader.interfaces.DataLoaderInterface;

public class DefaultDataLoader implements DataLoaderInterface {

    private static final Logger log = LoggerFactory.getLogger(DefaultDataLoader.class);

    private DataSet<? extends DataPoint> dataSet;
    private int batchSize = 0;
    private int currentBatch = 0;

    public DefaultDataLoader(int batchSize) {
        setBatchSize(batchSize);
    }

    @Override
    public <S extends DataSet<? extends DataPoint>> void setDataToIterate(S dataSet) {
        this.dataSet = dataSet;
        currentBatch = 0;
    }

    @Override
    public void setBatchSize(int batchSize) {
        this.batchSize = batchSize;
    }

    @Override
    public int getBatchSize() {
        return this.batchSize;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <S extends DataSet<? extends DataPoint>> S getBatch() {
        int lowerBatchNumber = (currentBatch++) * batchSize;
        int upperBatchNumber = ((currentBatch) * batchSize > dataSet.getNumberOfDataPoints())
                ? dataSet.getNumberOfDataPoints()
                : currentBatch * batchSize;
        return (S) dataSet.subset(lowerBatchNumber, upperBatchNumber);
    }

    @Override
    public int getBatchNumber() {
        return currentBatch;
    }

    // TODO:
    // last batch isn't calculated
    // This dataSet (40) at currentBatch 40 with batchSize 1 (40) has next: true
    // java.lang.IllegalArgumentException: dataPoints must not be empty.
    @Override
    public boolean hasNext() {
        boolean hasNext = currentBatch * batchSize < dataSet.getNumberOfDataPoints();
        log.debug("This dataSet ({}) at currentBatch {} with batchSize {} ({}) has next: {}",
                dataSet.getNumberOfDataPoints(), currentBatch, batchSize,
                currentBatch * batchSize, hasNext);
        return hasNext;
    }
}
