package de.schaack.ml.basics.models.implementation.classification;

import java.util.Arrays;
import java.util.stream.IntStream;

import de.schaack.ml.basics.data.interfaces.DataPoint;
import de.schaack.ml.basics.data.interfaces.DataSet;
import de.schaack.ml.basics.functions.math.MatrixOperations;
import de.schaack.ml.basics.models.interfaces.Model;
import de.schaack.ml.basics.settings.model.implementation.classification.PerceptronModelSettings;

public class Perceptron implements Model<Double> {

    private PerceptronModelSettings perceptronModelSettings;
    private double[] parameters;
    private boolean isInitialised = false;

    public Perceptron() {
        this(new PerceptronModelSettings());
    }

    public Perceptron(PerceptronModelSettings settings) {
        this.perceptronModelSettings = settings;
    }

    @Override
    public double feedForward(DataPoint dataPoint) {
        if (getParameters().length != dataPoint.getEntries().length)
            throw new IllegalArgumentException("Weights and dataPoint are not of equal size.");

        double matMulResult = MatrixOperations.valuesMulData(getParameters(), dataPoint);
        return perceptronModelSettings.getActivationFunction().activate(matMulResult);
    }

    @Override
    public double[] feedForward(DataSet trainingDataSet) {
        if (!isInitialised()) {
            throw new IllegalStateException("This model is not yet initialised.");
        }
        return IntStream.range(0, trainingDataSet.getDataPoints().length)
                .parallel()
                .mapToDouble(i -> MatrixOperations.valuesMulData(this.parameters, trainingDataSet.getDataPoints()[i]))
                .toArray();
    }

    @Override
    public void backPropagate(double outputOfPreviousComponent) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'backPropagate'");
    }

    @Override
    public void updateParameter(int index, double value) {
        this.parameters[index] = value;
    }

    @Override
    public void updateWeight(int index, double value) {
        updateParameter(index + 1, value);
    }

    /**
     * Perceptron prediction:
     * activationFunction(weights_T * dataPoint + bias) = y^
     */
    @Override
    public Double predict(DataPoint dataPoint) {
        if (dataPoint.hasLabel())
            throw new IllegalArgumentException("DataPoint has to be unlabeled.");
        else if (getParameters().length != dataPoint.getEntries().length)
            throw new IllegalArgumentException("Weights and dataPoint are not of equal size.");

        double matMulResult = MatrixOperations.valuesMulData(getParameters(), dataPoint);
        return perceptronModelSettings.getActivationFunction().activate(matMulResult);
    }

    @Override
    public Double[] predictions(DataPoint[] dataPoints) {
        return IntStream.range(0, dataPoints.length)
                .boxed()
                .map(i -> predict(dataPoints[i]))
                .toArray(Double[]::new);
    }

    @Override
    public Double[] predictions(DataSet dataSet) {
        return predictions(dataSet.getDataPoints());
    }

    @Override
    public PerceptronModelSettings getModelSettings() {
        return this.perceptronModelSettings;
    }

    @Override
    public double[] getParameters() {
        return parameters;
    }

    @Override
    public double[] getWeights() {
        return Arrays.copyOfRange(getParameters(), 0, parameters.length - 2);
    }

    @Override
    public double getBias() {
        return getParameters()[getParameters().length - 1];
    }

    @Override
    public double[] initialiseParameters(double[] initiationParameters) {
        if (isInitialised())
            throw new IllegalStateException("This model is already initialised");
        if (this.parameters.length != initiationParameters.length)
            throw new IllegalArgumentException(
                    "Initiation parameters don't match the size of the available parameters");

        this.parameters = initiationParameters;
        this.isInitialised = true;
        return getParameters();
    }

    @Override
    public boolean isInitialised() {
        return isInitialised;
    }
}
