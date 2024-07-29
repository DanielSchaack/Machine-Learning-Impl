package de.schaack.ml.basics;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.schaack.ml.basics.examples.models.PerceptronNandModel;

public class BasicsApplication {

    private static final Logger log = LoggerFactory.getLogger(BasicsApplication.class);

    public static void main(String[] args) {
        log.info("Hello, this is running from the command line!");

        PerceptronNandModel.showExample(25000, 0.2, 0.1, 20);
    }
}
