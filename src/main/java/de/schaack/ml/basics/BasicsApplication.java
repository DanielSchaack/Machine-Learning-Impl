package de.schaack.ml.basics;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
public class BasicsApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(BasicsApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// Command line logic here
		log.info("Hello, this is running from the command line!");

		// Example: Print all command line arguments
		if (args.length > 0) {
			log.info("Command line arguments:");
			for (String arg : args) {
				log.info(arg);
			}
		} else {
			log.info("No command line arguments found.");
		}
	}

}
