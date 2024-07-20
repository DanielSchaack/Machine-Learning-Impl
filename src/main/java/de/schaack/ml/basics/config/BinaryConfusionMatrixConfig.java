package de.schaack.ml.basics.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;

@Getter
@Configuration
public class BinaryConfusionMatrixConfig {

  @Value("${config.binary-confusion-matrix.parallel}")
  private boolean isParallel;

}
