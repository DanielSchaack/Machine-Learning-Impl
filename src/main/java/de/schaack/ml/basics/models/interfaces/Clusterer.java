package de.schaack.ml.basics.models.interfaces;

import de.schaack.ml.basics.data.DataRow;
import de.schaack.ml.basics.data.DataSet;

public interface Clusterer extends ModelInterface {
    int[] cluster(DataSet data);

    int predict(DataRow point);
}
