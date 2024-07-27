package de.schaack.ml.basics.functions.activation.interfaces;


public interface ActivationFunction {
   double activate(double value);

   double derivative(double activatedValue);
}

