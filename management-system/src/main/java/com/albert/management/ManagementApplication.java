package com.albert.management;

// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ManagementApplication {
  // private static final Logger log = LoggerFactory.getLogger(ManagementApplication.class);
  private static final Logger log = LogManager.getLogger(ManagementApplication.class);

  public static void main(String[] args) {
    SpringApplication.run(ManagementApplication.class, args);
    log.debug(
      "\n----------------------------------------------------------------------\n\t" + 
      "Application '{}' is running!\n" + 
      "----------------------------------------------------------------------",
      ManagementApplication.class.getSimpleName()
    );
  }
}
