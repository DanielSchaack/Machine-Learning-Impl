package de.schaack.ml.basics.pipelines;

import java.util.stream.IntStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.schaack.ml.basics.data.interfaces.DataPoint;
import de.schaack.ml.basics.data.interfaces.DataSet;
import de.schaack.ml.basics.functions.evaluation.interfaces.EvaluationFunction;
import de.schaack.ml.basics.functions.initialisation.implementation.ZeroWeightsInitialisation;
import de.schaack.ml.basics.functions.initialisation.interfaces.InitialisationFunction;
import de.schaack.ml.basics.functions.loader.interfaces.DataLoaderInterface;
import de.schaack.ml.basics.functions.loss.interfaces.LossFunction;
import de.schaack.ml.basics.models.interfaces.Model;

//R - Return class of the Model
public class SingleModelPipeline<R> {

    private static final Logger log = LoggerFactory.getLogger(SingleModelPipeline.class);

    private Model<R> model;
    private DataLoaderInterface dataLoader;
    private InitialisationFunction initialisationFunction = new ZeroWeightsInitialisation();
    private LossFunction lossFunction;
    private EvaluationFunction evaluationFunction;
    

    public SingleModelPipeline(Model<R> model, DataLoaderInterface dataLoader, LossFunction lossFunction) {
        this.model = model;
        this.dataLoader = dataLoader;
        this.lossFunction = lossFunction;
    }

    public SingleModelPipeline(Model<R> model, DataLoaderInterface dataLoader, LossFunction lossFunction,
            EvaluationFunction evaluationFunction) {
        this.model = model;
        this.dataLoader = dataLoader;
        this.lossFunction = lossFunction;
        this.evaluationFunction = evaluationFunction;
    }

    public SingleModelPipeline(Model<R> model, DataLoaderInterface dataLoader, LossFunction lossFunction,
            InitialisationFunction initialisationFunction) {
        this(model, dataLoader, lossFunction);
        this.initialisationFunction = initialisationFunction;
    }

    public SingleModelPipeline(Model<R> model, DataLoaderInterface dataLoader, LossFunction lossFunction,
            EvaluationFunction evaluationFunction,
            InitialisationFunction initialisationFunction) {
        this(model, dataLoader, lossFunction);
        this.evaluationFunction = evaluationFunction;
        this.initialisationFunction = initialisationFunction;
    }

    public Model<R> getModel() {
        return this.model;
    }

    // TODO
    public<S extends DataSet<? extends DataPoint>> void train(S trainSet, S testSet) {
        if (!this.getModel().isInitialised())
            this.getModel().initialiseParameters(
                    initialisationFunction.initializeWeights(trainSet.getDataPoint(0).getEntries().length));

        dataLoader.setDataToIterate(trainSet);

        while (dataLoader.hasNext()) {
            S batchDataSet = dataLoader.getBatch();
            double[][] result = IntStream.range(0, batchDataSet.getNumberOfDataPoints())
                    .parallel()
                    .mapToObj(i -> trainSingleDatapoint(batchDataSet.getDataPoint(i)))
                    .toArray(double[][]::new);
            double[] sumOfGradients = calculateSumOfGradients(result);
            model.updateParameters(sumOfGradients, dataLoader.getBatchSize());
        }
    }

    private double[] trainSingleDatapoint(DataPoint currentDataPoint) {
        double activatedSumOfProducts = model.feedForward(currentDataPoint);
        // debug
        //lossFunction.calculateLoss(currentDataPoint.getLabel(), activatedSumOfProducts);

        double lossDerivative = lossFunction.deriveLoss(currentDataPoint.getLabel(),
                activatedSumOfProducts);
        return model.backPropagate(currentDataPoint, lossDerivative);
    }

    /**
     * Calculates the sum of gradients from an array of gradient arrays.
     *
     * @param arrayOfGradientArrays An array of double arrays, where each double
     *                              array represents a set of gradients. All double
     *                              arrays must have the same length.
     * @return A double array representing the sum of the gradients.
     *         If the input array is empty or any of the sub-arrays has different
     *         lengths, an empty array is returned.
     */
    private double[] calculateSumOfGradients(double[][] arrayOfGradientArrays) {
        if (arrayOfGradientArrays.length == 0 || arrayOfGradientArrays[0].length == 0) {
            return new double[0];
        }

        int length = arrayOfGradientArrays[0].length;
        double[] sumOfGradients = new double[length];

        for (double[] gradientArray : arrayOfGradientArrays) {
            if (gradientArray.length != length)
                throw new IllegalArgumentException("All arrays must have the same length");

            for (int i = 0; i < length; i++) {
                sumOfGradients[i] += gradientArray[i];
            }
        }

        log.debug("The sum of all Gradients is: {}", sumOfGradients);
        return sumOfGradients;
    }
}
