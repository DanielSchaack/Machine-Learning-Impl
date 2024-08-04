package de.schaack.ml.basics.models.implementation.classification;

import java.util.Arrays;
import java.util.stream.IntStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.schaack.ml.basics.data.interfaces.DataPoint;
import de.schaack.ml.basics.data.interfaces.DataSet;
import de.schaack.ml.basics.functions.math.MatrixOperations;
import de.schaack.ml.basics.models.interfaces.Model;
import de.schaack.ml.basics.settings.model.implementation.classification.PerceptronModelSettings;

public class Perceptron implements Model<Double> {

    private static final Logger log = LoggerFactory.getLogger(Perceptron.class);

    private PerceptronModelSettings perceptronModelSettings;
    private double[] parameters;
    private double[] currentParameterGradients;
    private double currentSumOfProducts = 0;

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

            //TODO remove bias from datapoint
        this.currentSumOfProducts = MatrixOperations.valuesMulData(getParameters(), dataPoint);
        double currentActivatedSumOfProducts = perceptronModelSettings.getActivationFunction()
                .activate(currentSumOfProducts);
        log.debug(
                "By multiplying and summing current parameters {} with datapoint {} calculates currentSumOfProducts as {}, which activates into {}",
                getParameters(), dataPoint.getEntries(), this.currentSumOfProducts, currentActivatedSumOfProducts);
        return currentActivatedSumOfProducts;
    }

    // TODO Adjust for variably sized outputOfPreviousComponent (Layer-to-Layer)
    @Override
    public double[] backPropagate(DataPoint currentDataPoint, double outputOfPreviousComponent) {
        getModelSettings()
                .getActivationFunction()
                .deriveActivation(outputOfPreviousComponent);
        double[] parameterDerivatives = currentDataPoint.getEntries();
        this.currentParameterGradients = 
                        MatrixOperations.valuesMulValue(parameterDerivatives,
                                getModelSettings().getActivationFunction().getCurrentGradient());

        log.debug(
                "By multiplying and summing parameterDerivatives {} with (datapoint {} * previousGradient {})  calculates currentParameterGradients as {}",
                parameterDerivatives, currentDataPoint.getEntries(),
                getModelSettings().getActivationFunction().getCurrentGradient(),
                this.currentParameterGradients);

        return this.currentParameterGradients;
    }

    @Override
    public void updateParameters(double[] sumOfParameterGradients, int batchSize) {
        log.debug("Updating parameters {} with sumOfParameterGradients {} and batchSize {}",
                getParameters(), sumOfParameterGradients, batchSize);
        for (int i = 0; i < getParameters().length; i++) {
            this.parameters[i] = getModelSettings().getOptimiserFunction().calculateUpdate(this.parameters[i],
                    batchSize, currentParameterGradients[i]);
        }
        log.debug("Updated parameters to {}", getParameters());
        log.info("\n");
    }

    /**
     * Perceptron prediction:
     * activationFunction(weights_T * dataPoint + bias) = y^
     */
    @Override
    public Double predict(DataPoint dataPoint) {
        if (getParameters().length != dataPoint.getEntries().length)
            throw new IllegalArgumentException("Weights and dataPoint are not of equal size.");

        this.getModelSettings().getDataPreprocessor().preprocessDatapoint(dataPoint);

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
    public <S extends DataSet<? extends DataPoint>> Double[] predictions(S dataSet) {
        return predictions(dataSet.getDataPoints());
    }

    @SuppressWarnings("unchecked")
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

        this.parameters = initiationParameters;
        this.currentParameterGradients = new double[parameters.length];
        this.isInitialised = true;
        return getParameters();
    }

    @Override
    public boolean isInitialised() {
        return isInitialised;
    }
}
