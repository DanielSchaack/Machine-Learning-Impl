package de.schaack.ml.basics.models.interfaces.impl.classification;

import java.util.Map;

import de.schaack.ml.basics.data.DataRow;
import de.schaack.ml.basics.data.DataSet;
import de.schaack.ml.basics.models.BaseModel;
import de.schaack.ml.basics.models.interfaces.Classifier;
import de.schaack.ml.basics.models.settings.impl.PerceptronModelSettings;

public class Perceptron extends BaseModel implements Classifier {

    

    public Perceptron() {
        super(Perceptron.class.getSimpleName(), new PerceptronModelSettings());
    }

    
    @Override
    public void train(DataSet trainingData) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'train'");
    }

    @Override
    public int predict(DataRow point) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'predict'");
    }

    @Override
    public Map<Integer, Double> predictProbabilities(DataRow point) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'predictProbabilities'");
    }


}
