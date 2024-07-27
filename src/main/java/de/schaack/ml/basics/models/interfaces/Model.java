package de.schaack.ml.basics.models.interfaces;

import de.schaack.ml.basics.data.interfaces.DataPoint;
import de.schaack.ml.basics.data.interfaces.DataSet;
import de.schaack.ml.basics.settings.model.interfaces.ModelSettings;

public interface Model<R> {
    
    double feedForward(DataPoint dataPoint);

    double[] feedForward(DataSet trainingDataSet);

    void backPropagate(double outputOfPreviousComponent);

    void updateParameter(int index, double value);

    void updateWeight(int index, double value);

    R predict(DataPoint dataPoint);

    R[] predictions(DataPoint[] dataPoints);

    R[] predictions(DataSet dataSet);

    ModelSettings getModelSettings();

    double[] getParameters();

    double[] getWeights();

    double getBias();

    double[] initialiseParameters(double[] initiationParameters);

    boolean isInitialised();
}
