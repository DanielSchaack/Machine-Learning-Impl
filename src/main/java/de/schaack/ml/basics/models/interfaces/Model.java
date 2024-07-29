package de.schaack.ml.basics.models.interfaces;

import de.schaack.ml.basics.data.interfaces.DataPoint;
import de.schaack.ml.basics.data.interfaces.DataSet;
import de.schaack.ml.basics.settings.model.interfaces.ModelSettings;

public interface Model<R> {

    <P extends DataPoint> double feedForward(P dataPoint);

    <P extends DataPoint> double[] backPropagate(P currentDataPoint, double outputOfPreviousComponent);

    void updateParameters(double[] sumOfParameterGradients, int batchSize);

    <P extends DataPoint> R predict(P dataPoint);

    <P extends DataPoint> R[] predictions(P[] dataPoints);

    <S extends DataSet<? extends DataPoint>> R[] predictions(S dataSet);

    <M extends ModelSettings> M getModelSettings();

    double[] getParameters();

    double[] getWeights();

    double getBias();

    double[] initialiseParameters(double[] initiationParameters);

    boolean isInitialised();
}
