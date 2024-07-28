package de.schaack.ml.basics.functions.distribution;


//TODO
public class GaussianDistribution {
    private final double mean;
    private final double variance;

    public GaussianDistribution(double mean, double variance){
        this.mean = mean;
        this.variance = variance;
    }

    public double draw(){
        return 0.0;
    }

    public double[] draw(int amountOfDraws){
        return new double[0];
    }
}
