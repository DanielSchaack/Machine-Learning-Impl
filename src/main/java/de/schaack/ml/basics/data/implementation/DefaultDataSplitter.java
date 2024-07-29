package de.schaack.ml.basics.data.implementation;

import java.lang.reflect.Array;

import de.schaack.ml.basics.data.interfaces.DataPoint;
import de.schaack.ml.basics.data.interfaces.DataSet;
import de.schaack.ml.basics.data.interfaces.DataSplitter;
import de.schaack.ml.basics.functions.util.SplittingUtil;

public class DefaultDataSplitter implements DataSplitter {

    @SuppressWarnings("unchecked")
    @Override
    public <T extends DataSet<? extends DataPoint>> T[] split(T dataSet, int testSizePercentage) {
        int[] indices = SplittingUtil.getIndicesBasedOnPercentage(dataSet.getNumberOfDataPoints(), testSizePercentage);
        T[] result = (T[]) Array.newInstance(dataSet.getClass(), 2);
        result[0] = (T) dataSet.subset(indices[0], indices[1]);
        result[1] = (T) dataSet.subset(indices[2], indices[3]);
        return result;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T extends DataSet<? extends DataPoint>> T[] split(T dataSet, int testSizePercentage, int validationSizePercentage) {
        int[] indices = SplittingUtil.getIndicesBasedOnPercentages(dataSet.getNumberOfDataPoints(), testSizePercentage,
                validationSizePercentage);
        T[] result = (T[]) Array.newInstance(dataSet.getClass(), 3);
        result[0] = (T) dataSet.subset(indices[0], indices[1]);
        result[1] = (T) dataSet.subset(indices[2], indices[3]);
        result[2] = (T) dataSet.subset(indices[4], indices[5]);

        return result;
    }

}
