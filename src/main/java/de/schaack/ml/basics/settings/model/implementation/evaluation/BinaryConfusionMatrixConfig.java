package de.schaack.ml.basics.settings.model.implementation.evaluation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.schaack.ml.basics.config.interfaces.ParallelSettings;
import de.schaack.ml.basics.config.properties.ConfigReader;

public class BinaryConfusionMatrixConfig implements ParallelSettings {

  private static final Logger log = LoggerFactory.getLogger(BinaryConfusionMatrixConfig.class);

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
    log.info("BinaryConfusionMatrixConfig: isParallel={}", isParallel);
  }
}
