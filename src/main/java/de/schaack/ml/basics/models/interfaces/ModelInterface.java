package de.schaack.ml.basics.models.interfaces;

import de.schaack.ml.basics.data.interfaces.DataSet;

public interface ModelInterface {    
    void train(DataSet trainingData);

}
