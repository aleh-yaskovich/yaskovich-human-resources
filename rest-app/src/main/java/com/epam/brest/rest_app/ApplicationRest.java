package com.epam.brest.rest_app;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@SpringBootApplication
@ComponentScan(basePackages = "com.epam.brest")
@OpenAPIDefinition(
        info = @Info(
                title = "Human Resources",
                description = "Human resources rest-application",
                termsOfService = "https://github.com/aleh-yaskovich/yaskovich-human-resources",
                contact = @Contact(
                        name = "Aleh Yaskovich",
                        url = "epam.com",
                        email = "aleh.yaskovich@epam.com"
                ),
                license = @License(
                        name = "",
                        url =""
                ),
                version = "1.0"
        )
)
public class ApplicationRest {

    public static void main(String[] args) {
        SpringApplication.run(ApplicationRest.class, args);
    }
}
