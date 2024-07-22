package de.schaack.ml.basics.models.implementation.classification;

import java.util.Map;

import de.schaack.ml.basics.data.interfaces.DataPoint;
import de.schaack.ml.basics.data.interfaces.DataSet;
import de.schaack.ml.basics.models.BaseModel;
import de.schaack.ml.basics.models.interfaces.Classifier;
import de.schaack.ml.basics.settings.implementation.PerceptronModelSettings;

public class Perceptron extends BaseModel implements Classifier {

    

    public Perceptron() {
        super(new PerceptronModelSettings());
    }

    public Perceptron(PerceptronModelSettings settings) {
        super(settings);
    }

    
    @Override
    public void train(DataSet trainingData) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'train'");
    }

    @Override
    public int predict(DataPoint point) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'predict'");
    }

    @Override
    public Map<Integer, Double> predictProbabilities(DataPoint point) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'predictProbabilities'");
    }


}
