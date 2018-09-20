 package com.sound;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

//import java.util.concurrent.CountDownLatch;

@SpringBootApplication
public class Application {
	private static final Logger logger = LoggerFactory.getLogger(Application.class);
    public static void main(String[] args) {
    	logger.info("start spring boot .....");
		ApplicationContext ctx = SpringApplication.run(Application.class, args);
    }
}