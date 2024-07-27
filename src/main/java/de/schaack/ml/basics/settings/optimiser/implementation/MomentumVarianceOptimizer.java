package de.schaack.ml.basics.settings.optimiser.implementation;

import de.schaack.ml.basics.settings.optimiser.interfaces.OptimiserSettings;
import de.schaack.ml.basics.settings.optimiser.interfaces.extensions.MomentumOptimiserSettings;
import de.schaack.ml.basics.settings.optimiser.interfaces.extensions.MomentumVarianceOptimiserSettings;
import de.schaack.ml.basics.settings.optimiser.interfaces.extensions.VarianceOptimiserSettings;

public class MomentumVarianceOptimizer implements MomentumVarianceOptimiserSettings{

    @Override
    public double getMomentumRate() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getMomentumRate'");
    }

    @Override
    public MomentumOptimiserSettings setMomentumRate(double momentum) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setMomentumRate'");
    }

    @Override
    public double getMomentum() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getMomentum'");
    }

    @Override
    public void updateMomentum(double momentum) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateMomentum'");
    }

    @Override
    public double getLearningRate() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getLearningRate'");
    }

    @Override
    public OptimiserSettings setLearningRate(double learningRate) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setLearningRate'");
    }

    @Override
    public double getVarianceRate() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getVarianceRate'");
    }

    @Override
    public VarianceOptimiserSettings setVarianceRate(double variance) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setVarianceRate'");
    }

    @Override
    public double getVariance() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getVariance'");
    }

    @Override
    public void updateVariance(double variance) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateVariance'");
    }
    
}
