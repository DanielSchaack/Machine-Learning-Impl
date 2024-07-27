package de.schaack.ml.basics.functions.optimizer.interfaces;

import de.schaack.ml.basics.settings.optimiser.interfaces.OptimiserSettings;

public interface OptimiserFunction {
    OptimiserSettings getSettings();

    double[] calculateUpdates(double[] valuesOld, double gradient);
    double calculateUpdate(double valueOld, double gradient);
}
