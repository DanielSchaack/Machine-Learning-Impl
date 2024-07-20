package de.schaack.ml.basics.models.settings.impl;

import java.util.HashMap;
import java.util.Map;

import de.schaack.ml.basics.functions.activation.ActivationFunction;
import de.schaack.ml.basics.functions.activation.impl.SigmoidActivation;
import de.schaack.ml.basics.models.settings.ModelSettings;

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
