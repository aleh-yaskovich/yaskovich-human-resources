package com.epam.brest.web_app;

import com.epam.brest.service.DepartmentDtoService;
import com.epam.brest.service.DepartmentService;
import com.epam.brest.service.EmployeeService;
import com.epam.brest.service.rest.DepartmentDtoServiceRest;
import com.epam.brest.service.rest.DepartmentServiceRest;
import com.epam.brest.service.rest.EmployeeServiceRest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
public class WebConfig {

    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate(new SimpleClientHttpRequestFactory());
    }

    @Bean
    DepartmentDtoService departmentDtoService() {
        return new DepartmentDtoServiceRest(restTemplate());
    }

    @Bean
    DepartmentService DepartmentService() {
        return new DepartmentServiceRest(restTemplate());
    }

    @Bean
    EmployeeService EmployeeService() {
        return new EmployeeServiceRest(restTemplate());
    }
}
