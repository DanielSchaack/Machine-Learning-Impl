package de.schaack.ml.basics.models.interfaces;

import de.schaack.ml.basics.data.interfaces.DataPoint;

public interface Regressor extends ModelInterface {
    double predict(DataPoint point);
}
