package de.schaack.ml.basics.pipelines;

import java.util.stream.IntStream;

import de.schaack.ml.basics.data.interfaces.DataPoint;
import de.schaack.ml.basics.data.interfaces.DataSet;
import de.schaack.ml.basics.functions.evaluation.interfaces.EvaluationFunction;
import de.schaack.ml.basics.functions.initialisation.implementation.ZeroWeightsInitialisation;
import de.schaack.ml.basics.functions.initialisation.interfaces.InitialisationFunction;
import de.schaack.ml.basics.functions.loader.interfaces.DataLoaderInterface;
import de.schaack.ml.basics.functions.loss.interfaces.LossFunction;
import de.schaack.ml.basics.functions.optimizer.implementation.StochasticGradientDescent;
import de.schaack.ml.basics.functions.optimizer.interfaces.OptimiserFunction;
import de.schaack.ml.basics.models.interfaces.Model;

//R - Return class of the Model
public class SingleModelPipeline<R> {

    private Model<R> model;
    private DataLoaderInterface dataLoader;
    private OptimiserFunction optimiserFunction = new StochasticGradientDescent(0.1);
    private InitialisationFunction initialisationFunction = new ZeroWeightsInitialisation();
    private LossFunction lossFunction;
    private EvaluationFunction evaluationFunction;

    public SingleModelPipeline(Model<R> model, DataLoaderInterface dataLoader, LossFunction lossFunction,
            EvaluationFunction evaluationFunction) {
        this.model = model;
        this.dataLoader = dataLoader;
        this.lossFunction = lossFunction;
        this.evaluationFunction = evaluationFunction;
    }

    public SingleModelPipeline(Model<R> model, DataLoaderInterface dataLoader, LossFunction lossFunction,
            EvaluationFunction evaluationFunction,
            OptimiserFunction optimiserFunction) {
        this(model, dataLoader, lossFunction, evaluationFunction);
        this.optimiserFunction = optimiserFunction;
    }

    public SingleModelPipeline(Model<R> model, DataLoaderInterface dataLoader, LossFunction lossFunction,
            EvaluationFunction evaluationFunction,
            InitialisationFunction initialisationFunction) {
        this(model, dataLoader, lossFunction, evaluationFunction);
        this.initialisationFunction = initialisationFunction;
    }

    public SingleModelPipeline(Model<R> model, DataLoaderInterface dataLoader, LossFunction lossFunction,
            EvaluationFunction evaluationFunction,
            OptimiserFunction optimiserFunction,
            InitialisationFunction initialisationFunction) {
        this(model, dataLoader, lossFunction, evaluationFunction);
        this.optimiserFunction = optimiserFunction;
        this.initialisationFunction = initialisationFunction;
    }

    public Model<R> getModel() {
        return this.model;
    }

    public DataLoaderInterface getDataLoaderInterface() {
        return this.dataLoader;
    }

    public OptimiserFunction getOptimiserFunction() {
        return this.optimiserFunction;
    }

    public InitialisationFunction getInitialisationFunction() {
        return this.initialisationFunction;
    }

    public LossFunction getLossFunction() {
        return this.lossFunction;
    }

    public EvaluationFunction getEvaluationFunction() {
        return this.evaluationFunction;
    }

    public void train(DataSet dataSet) {
        if (!this.getModel().isInitialised())
            this.getModel().initialiseParameters(initialisationFunction.initializeWeights(dataSet.getDataPoint(0).getEntries().length));

        dataLoader.setDataToIterate(dataSet);

        while (dataLoader.hasNext()) {
            DataSet batchDataSet = dataLoader.getBatch(dataLoader.getBatchNumber());
            IntStream.range(0, batchDataSet.getNumberOfDataPoints())
                    .parallel()
                    .forEach(i -> trainSingleDatapoint(batchDataSet.getDataPoint(i)));
        }
    }

    private void trainSingleDatapoint(DataPoint currentDataPoint) {
        double activatedSumOfProducts = model.feedForward(currentDataPoint);
        double loss = lossFunction.calculateLoss(currentDataPoint.getLabel(), activatedSumOfProducts);

        double lossDerivative = lossFunction.derivativeLoss(currentDataPoint.getLabel(),
                activatedSumOfProducts);
        double activatedSumOfProductsDerivative = model.getModelSettings()
                .getActivationFunction()
                .derivative(activatedSumOfProducts);
        double[] weightDerivatives = currentDataPoint.getNonBias();
        IntStream.range(0, weightDerivatives.length - 1)
                .parallel()
                .forEach(i -> updateWeight(model, i, lossDerivative, activatedSumOfProductsDerivative,
                        weightDerivatives[i]));
    }

    private void updateWeight(Model<R> model, int i, double... derivatives) {
        double gradient = 1.0;
        for (double d : derivatives) {
            gradient *= d;
        }
        model.updateWeight(i, optimiserFunction.calculateUpdate(model.getParameters()[i + 1], gradient));
    }
}
