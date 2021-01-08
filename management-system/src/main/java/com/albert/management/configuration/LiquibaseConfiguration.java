package com.albert.management.configuration;

import javax.sql.DataSource;
import liquibase.integration.spring.SpringLiquibase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LiquibaseConfiguration {

  public LiquibaseConfiguration() {}

  @Bean
  public SpringLiquibase liquibase(DataSource dataSource) {
    SpringLiquibase liquibase = new SpringLiquibase();
    liquibase.setChangeLog("classpath:liquibase/master.xml");
    liquibase.setDataSource(dataSource);
    return liquibase;
  }
}
