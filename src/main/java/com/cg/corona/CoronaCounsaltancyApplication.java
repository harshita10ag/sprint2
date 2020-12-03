package com.cg.corona;

import org.jboss.logging.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CoronaCounsaltancyApplication {

	public static void main(String[] args) {
		SpringApplication.run(CoronaCounsaltancyApplication.class, args);
		Logger logger=Logger.getLogger(CoronaCounsaltancyApplication.class);
		logger.info("Open");

	}

}
