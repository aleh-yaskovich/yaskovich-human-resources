package com.epam.brest.service.rest;

import com.epam.brest.entity.Employee;
import com.epam.brest.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceRest implements EmployeeService {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeServiceRest.class);

    private String url = "http://localhost:8080/employees";
    private RestTemplate restTemplate;

    public EmployeeServiceRest(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public List<Employee> findAllEmployees() {
        LOGGER.debug("findAllEmployees()");
        ResponseEntity responseEntity = restTemplate.getForEntity(url, List.class);
        return (List<Employee>) responseEntity.getBody();
    }

    @Override
    public Optional<Employee> findEmployeeById(Long id) {
        LOGGER.debug("findEmployeeById({})", id);
        ResponseEntity<Employee> responseEntity =
                restTemplate.getForEntity(url + "/" + id, Employee.class);
        return Optional.ofNullable(responseEntity.getBody());
    }

    @Override
    public Employee createEmployee(Employee employee) {
        LOGGER.debug("createEmployee({})", employee);
        ResponseEntity responseEntity = restTemplate.postForEntity(url, employee, Employee.class);
        return (Employee) responseEntity.getBody();
    }

    @Override
    public Employee updateEmployee(Employee employee) {
        LOGGER.debug("updateEmployee({})", employee);
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<Employee> entity = new HttpEntity<>(employee, headers);
        ResponseEntity<Employee> result = restTemplate.exchange(url, HttpMethod.PUT, entity, Employee.class);
        return (Employee) result.getBody();
    }

    @Override
    public void deleteEmployeeById(Long id) {
        LOGGER.debug("deleteEmployeeById({})", id);
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<Employee> entity = new HttpEntity<>(headers);
        ResponseEntity<Integer> result =
                restTemplate.exchange(url + "/" + id, HttpMethod.DELETE, entity, Integer.class);
    }
}
