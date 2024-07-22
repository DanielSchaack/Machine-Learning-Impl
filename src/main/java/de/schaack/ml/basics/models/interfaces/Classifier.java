package de.schaack.ml.basics.models.interfaces;

import java.util.Map;

import de.schaack.ml.basics.data.DataPoint;

public interface Classifier extends ModelInterface {
    int predict(DataPoint point);

    Map<Integer, Double> predictProbabilities(DataPoint point);
}
