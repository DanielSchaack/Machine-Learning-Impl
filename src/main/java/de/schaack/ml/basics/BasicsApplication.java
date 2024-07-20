package de.schaack.ml.basics;

import java.util.List;
import java.util.logging.Logger;

import de.schaack.ml.basics.config.logging.BufferingLogHandler;
import de.schaack.ml.basics.config.logging.LoggerConfig;
import de.schaack.ml.basics.config.logging.LoggerUtil;

public class BasicsApplication {

	private static Logger log = LoggerUtil.getLogger(BasicsApplication.class.getName());

	public static void main(String... args) {
		run(args);
	}

	private static void run(String[] args) {
		try {
			// Main logic
			mainLogic(args);
		} finally {
			// Post-execution code
			post();
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
