package de.schaack.ml.basics.config.implementations;

import de.schaack.ml.basics.config.interfaces.ParallelSettings;
import de.schaack.ml.basics.config.properties.ConfigReader;

public class BinaryConfusionMatrixConfig implements ParallelSettings {

  private boolean isParallel = false;

  static {
    new BinaryConfusionMatrixConfig().init();
  }

  @Override
  public boolean isParallel() {
    return isParallel;
  }

  @Override
  public void init() {
    String isParallelPropertyString = ConfigReader.getProperty("ml-basics.binary-confusion-matrix.parallel");
    this.isParallel = Boolean.valueOf(isParallelPropertyString);
    System.out.println("BinaryConfusionMatrixConfig: isParallel=" + isParallel);
  }
}
