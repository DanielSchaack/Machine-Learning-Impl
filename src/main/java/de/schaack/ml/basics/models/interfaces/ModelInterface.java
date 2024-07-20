package de.schaack.ml.basics.models.interfaces;

import de.schaack.ml.basics.data.DataSet;

public interface ModelInterface {    
    void train(DataSet trainingData);

}
