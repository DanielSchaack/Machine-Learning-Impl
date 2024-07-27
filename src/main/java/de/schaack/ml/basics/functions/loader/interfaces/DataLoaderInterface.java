package de.schaack.ml.basics.functions.loader.interfaces;

import de.schaack.ml.basics.data.interfaces.DataSet;

public interface DataLoaderInterface {
    void setDataToIterate(DataSet dataSet);
    void setBatchSize(int batchSize);
    int getBatchNumber();
    DataSet getBatch(int batchNumber);
    boolean hasNext();
}
