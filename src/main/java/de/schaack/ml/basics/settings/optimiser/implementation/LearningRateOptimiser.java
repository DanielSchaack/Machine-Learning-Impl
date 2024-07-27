package de.schaack.ml.basics.settings.optimiser.implementation;

import de.schaack.ml.basics.settings.optimiser.interfaces.OptimiserSettings;

public class LearningRateOptimiser implements OptimiserSettings {

    private double learningRate = 0.1;

    public LearningRateOptimiser(){
        
    }

    public LearningRateOptimiser(double learningRate){
        this.learningRate = learningRate;
    }

    @Override
    public double getLearningRate() {
        return this.learningRate;
    }

    @Override
    public OptimiserSettings setLearningRate(double learningRate) {
        this.learningRate = learningRate;
        return this;
    }

}
