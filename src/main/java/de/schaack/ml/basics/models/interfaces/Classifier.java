package de.schaack.ml.basics.models.interfaces;

import java.util.Map;

import de.schaack.ml.basics.data.DataRow;

public interface Classifier extends ModelInterface {
    int predict(DataRow point);

    Map<Integer, Double> predictProbabilities(DataRow point);
}
