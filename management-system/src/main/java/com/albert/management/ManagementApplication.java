package com.albert.management;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ManagementApplication
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );

        // 執行 Spring Boot
        SpringApplication.run(ManagementApplication.class, args);
    }
}
