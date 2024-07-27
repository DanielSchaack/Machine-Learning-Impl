package de.schaack.ml.basics;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BasicsApplication {

	private static final Logger log = LoggerFactory.getLogger(BasicsApplication.class);

	public static void main(String... args) {
		run(args);
	}

	private static void run(String[] args) {
		log.info("Hello, this is running from the command line!");
	}
}
