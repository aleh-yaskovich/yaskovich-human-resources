package com.epam.brest.rest_app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@SpringBootApplication
@ComponentScan(basePackages = "com.epam.brest")
public class ApplicationRest {

    public static void main(String[] args) {
        SpringApplication.run(ApplicationRest.class, args);
    }
}
