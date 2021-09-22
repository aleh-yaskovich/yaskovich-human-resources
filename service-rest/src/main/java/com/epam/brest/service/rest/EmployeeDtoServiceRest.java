package com.epam.brest.service.rest;

import com.epam.brest.entity.EmployeeDto;
import com.epam.brest.service.EmployeeDtoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class EmployeeDtoServiceRest implements EmployeeDtoService {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeDtoServiceRest.class);

    private String url = "http://localhost:8080/employees";
    private RestTemplate restTemplate;

    public EmployeeDtoServiceRest(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    /**
     * Return all employees with department names
     **/
    @Override
    public List<EmployeeDto> findAllEmployees() {
        LOGGER.debug("findAllEmployees()");
        ResponseEntity responseEntity = restTemplate.getForEntity(url + "-dto", List.class);
        return (List<EmployeeDto>) responseEntity.getBody();
    }
}
