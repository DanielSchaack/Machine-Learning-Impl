package de.schaack.ml.basics.examples.models;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import org.knowm.xchart.HeatMapChart;
import org.knowm.xchart.HeatMapChartBuilder;
import org.knowm.xchart.HeatMapSeries;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;
import org.knowm.xchart.XYSeries.XYSeriesRenderStyle;
import org.knowm.xchart.style.Styler.LegendPosition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.schaack.ml.basics.data.implementation.DefaultDataPoint;
import de.schaack.ml.basics.data.implementation.DefaultDataPreprocessor;
import de.schaack.ml.basics.data.implementation.DefaultDataSet;
import de.schaack.ml.basics.data.implementation.DefaultDataSplitter;
import de.schaack.ml.basics.functions.activation.implementation.SigmoidActivation;
import de.schaack.ml.basics.functions.distribution.GaussianDistribution;
import de.schaack.ml.basics.functions.initialisation.implementation.XavierInitialisation;
import de.schaack.ml.basics.functions.initialisation.implementation.ZeroWeightsInitialisation;
import de.schaack.ml.basics.functions.loader.implementation.DefaultDataLoader;
import de.schaack.ml.basics.functions.loss.implementation.BinaryCrossEntropyLoss;
import de.schaack.ml.basics.functions.optimizer.implementation.StochasticGradientDescent;
import de.schaack.ml.basics.models.implementation.classification.Perceptron;
import de.schaack.ml.basics.pipelines.SingleModelPipeline;
import de.schaack.ml.basics.settings.model.implementation.classification.PerceptronModelSettings;

public class PerceptronNandModel {

    private static final Logger log = LoggerFactory.getLogger(PerceptronNandModel.class);

    private PerceptronNandModel() {
    }

    public Perceptron train(int amountCoordinates, double standardDeviation, double learningRate, int batchSize, int epochAmount) {
        PerceptronModelSettings modelSettings = new PerceptronModelSettings()
                .setActivationFunction(new SigmoidActivation())
                .setOptimiserFunction(new StochasticGradientDescent(learningRate))
                .setDataPreprocessor(new DefaultDataPreprocessor())
                .setNumberOfEpochs(epochAmount);
        Perceptron perceptron = new Perceptron(modelSettings);
        SingleModelPipeline<Double> singleModelPipeline = new SingleModelPipeline<>(perceptron,
                new DefaultDataLoader(batchSize),
                new BinaryCrossEntropyLoss(),
                null,
                new XavierInitialisation());

        DefaultDataSplitter dataSplitter = new DefaultDataSplitter();
        List<DefaultDataPoint> dataPoints = generateDataPoints(amountCoordinates, standardDeviation);
        DefaultDataSet dataSet = new DefaultDataSet(dataPoints, true);
        dataSet.shuffle();
        DefaultDataSet[] trainAndTestSet = dataSplitter.split(dataSet, 30);
        modelSettings.getDataPreprocessor().fitToDataSet(trainAndTestSet[0]);
        singleModelPipeline.train(trainAndTestSet[0], trainAndTestSet[1]);
        showResults(dataPoints, perceptron);
        return perceptron;
    }

    private HeatMapChart showHeatmap(Perceptron perceptron) {

        int rows = 101;
        int cols = 101;
        List<Number> xData = generateIncrementingList(-1.0, 2.0, rows);
        List<Number> yData = generateIncrementingList(-1.0, 2.0, cols);
        List<Number[]> heatData = new ArrayList<>();

        for (int i = 0; i < xData.size(); i++) {
            double x1 = ((Double) xData.get(i)).doubleValue();
            for (int j = 0; j < yData.size(); j++) {
                double x2 = ((Double) yData.get(j)).doubleValue();
                double prediction = perceptron.predict(
                        PerceptronNandModel.getCoordinateDataPoint(x1, x2, 0));
                Number[] numbers = { i, j, prediction };
                heatData.add(numbers);
                log.info("Prediction for ({}, {}): {}", x1, x2, prediction);
            }
        }

        // Create Chart
        HeatMapChart chart = new HeatMapChartBuilder().width(800).height(600).build();

        Color[] rangeColors = { new Color(255, 0, 0), new Color(255, 255, 255), new Color(0, 0, 255) };
        chart.getStyler().setRangeColors(rangeColors);

        HeatMapSeries heatMapSeries = chart.addSeries("heatMap", xData, yData, heatData);
        heatMapSeries.setMin(0);
        heatMapSeries.setMax(1);

        return chart;
    }

    public List<Number> generateIncrementingList(double start, double end, int totalPoints) {
        List<Number> data = new ArrayList<>();
        for (int i = 0; i < totalPoints; i++) {
            double value = ((double) i / (totalPoints - 1)) * (end - start) + start;
            data.add(value);
        }
        return data;
    }

    private List<DefaultDataPoint> generateDataPoints(int amountCoordinates, double standardDeviation) {
        List<DefaultDataPoint> dataPoints = new ArrayList<>();
        for (int i = 0; i < amountCoordinates; i++) {
            dataPoints.add(getCoordinateDataPoint(0.0, 0.0, 0.0, standardDeviation));
            dataPoints.add(getCoordinateDataPoint(0.0, 1.0, 1.0, standardDeviation));
            dataPoints.add(getCoordinateDataPoint(1.0, 0.0, 1.0, standardDeviation));
            dataPoints.add(getCoordinateDataPoint(1.0, 1.0, 1.0, standardDeviation));
        }
        return dataPoints;
    }

    public static DefaultDataPoint getCoordinateDataPoint(double xCoordinate, double yCoordinate,
            double standardDeviation) {
        double[] features = GaussianDistribution.generateCoordinates(xCoordinate, yCoordinate,
                standardDeviation);
        return new DefaultDataPoint(features, true);
    }

    public static DefaultDataPoint getCoordinateDataPoint(double xCoordinate, double yCoordinate, Double label,
            double standardDeviation) {
        double[] features = GaussianDistribution.generateCoordinates(xCoordinate, yCoordinate,
                standardDeviation);
        return new DefaultDataPoint(features, label, true);
    }

    public static void showExample(int amountCoordinates, double standardDeviation, double learningRate,
            int batchSize, int epochAmount) {

        new PerceptronNandModel().train(amountCoordinates,
                standardDeviation,
                learningRate,
                batchSize,
                epochAmount);
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    private void showResults(List<DefaultDataPoint> dataPoints, Perceptron perceptron) {

        log.info("\n\n\nModel parameters: {}\n\n\n", perceptron.getParameters());

        List<DefaultDataPoint> labelZeroPoints = dataPoints.stream()
                .filter(datapoint -> datapoint.getLabel() == 0.0)
                .toList();
        List<DefaultDataPoint> labelOnePoints = dataPoints.stream()
                .filter(datapoint -> datapoint.getLabel() == 1.0)
                .toList();

        // Series
        List<Double> xZeroData = labelZeroPoints.stream().mapToDouble(datapoint -> datapoint.getEntry(1))
                .boxed()
                .toList();
        List<Double> yZeroData = labelZeroPoints.stream().mapToDouble(datapoint -> datapoint.getEntry(2))
                .boxed()
                .toList();

        List<Double> xOneData = labelOnePoints.stream().mapToDouble(datapoint -> datapoint.getEntry(1)).boxed()
                .toList();
        List<Double> yOneData = labelOnePoints.stream().mapToDouble(datapoint -> datapoint.getEntry(2)).boxed()
                .toList();

        // Create Chart
        XYChart chart = new XYChartBuilder().width(600).height(500).title("Gaussian Blobs").xAxisTitle("X")
                .yAxisTitle("Y").build();

        chart.addSeries("Zero labeled", xZeroData, yZeroData);
        chart.addSeries("One labeled", xOneData, yOneData);

        // Customize Chart
        chart.getStyler().setDefaultSeriesRenderStyle(XYSeriesRenderStyle.Scatter);
        chart.getStyler().setChartTitleVisible(false);
        chart.getStyler().setLegendPosition(LegendPosition.InsideSW);
        chart.getStyler().setMarkerSize(16);

        new SwingWrapper(List.of(chart, showHeatmap(perceptron))).displayChartMatrix();
    }
}
