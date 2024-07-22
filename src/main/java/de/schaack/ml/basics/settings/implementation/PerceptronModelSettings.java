package de.schaack.ml.basics.settings.implementation;

import java.util.HashMap;
import java.util.Map;

import de.schaack.ml.basics.functions.activation.implementation.SigmoidActivation;
import de.schaack.ml.basics.functions.activation.interfaces.ActivationFunction;
import de.schaack.ml.basics.settings.interfaces.ModelSettings;

public class PerceptronModelSettings implements ModelSettings {
    private double learningRate = 0.1;
    private ActivationFunction activationFunction = new SigmoidActivation();


    @Override
    public Map<String, Object> getParameters() {
        Map<String, Object> params = new HashMap<>();
        params.put("learningRate", learningRate);
        params.put("activationFunction", activationFunction);
        return params;
    }
}
