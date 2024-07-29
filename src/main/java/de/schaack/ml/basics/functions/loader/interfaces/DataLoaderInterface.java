package de.schaack.ml.basics.functions.loader.interfaces;

import de.schaack.ml.basics.data.interfaces.DataPoint;
import de.schaack.ml.basics.data.interfaces.DataSet;

public interface DataLoaderInterface {
    <S extends DataSet<? extends DataPoint>> void setDataToIterate(S dataSet);

    void setBatchSize(int batchSize);

    int getBatchSize();

    int getBatchNumber();

    <S extends DataSet<? extends DataPoint>> S getBatch();

    boolean hasNext();
}
