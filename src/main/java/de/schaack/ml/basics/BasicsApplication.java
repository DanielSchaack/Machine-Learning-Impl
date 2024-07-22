package de.schaack.ml.basics;

import de.schaack.ml.basics.config.logging.LoggingUtils;

public class BasicsApplication {

	public static void main(String... args) {
		run(args);
	}

	private static void run(String[] args) {
		LoggingUtils.info("Hello, this is running from the command line!");
	}
}
