package de.schaack.ml.basics;

import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import de.schaack.ml.basics.config.logging.BufferingLogHandler;
import de.schaack.ml.basics.config.logging.LoggerConfig;
import de.schaack.ml.basics.config.logging.LoggerUtil;
import de.schaack.ml.basics.config.properties.ConfigReader;

public class BasicsApplication {
	private static final Logger log = LoggerUtil.getLogger(BasicsApplication.class.getName());

	public static void main(String... args) {
		run(args);
	}

	private static void run(String[] args) {
		// Initialization code
		init();

		try {
			// Main logic
			mainLogic(args);
		} finally {
			// Post-execution code
			post();
		}
	}

	private static void init() {
		// Reference to ConfigReader will force its initialization
		System.out.println(new Date() + " Initializing application...");

		if (ConfigReader.getProperties() != null) {
			System.out.println(new Date() + " Configuration files loaded successfully.");
			ConfigReader.getProperties().forEach((key, value) -> log.config(key + "=" + value));
		} else {
			System.err.println(new Date() + " Failed to load configuration files.");
		}
	}

	private static void mainLogic(String[] args) {
		log.info("Hello, this is running from the command line!");
	}

	private static void post() {
		// Post-execution logic (e.g., releasing resources, logging final messages)
		log.info("Post-execution started.");

		// Retrieve buffered log messages
		BufferingLogHandler handler = LoggerConfig.getBufferingHandler();
		List<String> logMessages = handler.getBuffer();

		// Print buffered messages
		for (String message : logMessages) {
			System.out.println(message);
		}
	}
}
