package de.schaack.ml.basics.models.interfaces;

import de.schaack.ml.basics.data.interfaces.DataPoint;
import de.schaack.ml.basics.data.interfaces.DataSet;

public interface Clusterer extends ModelInterface {
    int[] cluster(DataSet data);

    int predict(DataPoint point);
}
