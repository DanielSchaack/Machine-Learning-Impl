package de.schaack.ml.basics.functions.optimizer.interfaces;

import de.schaack.ml.basics.settings.optimiser.interfaces.OptimiserSettings;

public interface OptimiserFunction {
    OptimiserSettings getSettings();
    double calculateUpdate(double valueOld, int batchSize, double gradient);
}
