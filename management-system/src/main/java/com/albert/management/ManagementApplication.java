package com.albert.management;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ManagementApplication {

    private static final org.slf4j.Logger logger_slf4j = org.slf4j.LoggerFactory.getLogger(ManagementApplication.class);
    private static final org.apache.logging.log4j.Logger logger_log4j = org.apache.logging.log4j.LogManager.getLogger(ManagementApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(ManagementApplication.class, args);
        logger_slf4j.info("Hi, {}", "SLF4J");
        logger_log4j.info("Hi, {}", "LOG4J");
    }
}
