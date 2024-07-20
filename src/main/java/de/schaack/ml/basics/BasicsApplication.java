package de.schaack.ml.basics;

import de.schaack.ml.basics.config.BinaryConfusionMatrixConfig;
import de.schaack.ml.basics.config.ConfigReader;

public class BasicsApplication {

	public static void main(String... args) {
		init();

		// Command line logic here
		System.out.println("Hello, this is running from the command line!");

	}

	private static void init() {
		// Reference to ConfigReader will force its initialization
		System.out.println("Initializing application...");

		if (ConfigReader.getProperties() != null) {
			System.out.println("Configuration files loaded successfully.");
			ConfigReader.getProperties().forEach((key, value) -> System.out.println(key + "=" + value));
		} else {
			System.out.println("Failed to load configuration files.");
		}
	}
}
