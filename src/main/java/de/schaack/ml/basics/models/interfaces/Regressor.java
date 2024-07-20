package de.schaack.ml.basics.models.interfaces;

import de.schaack.ml.basics.data.DataRow;

public interface Regressor extends ModelInterface {
    double predict(DataRow point);
}
