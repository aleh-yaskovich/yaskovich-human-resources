package com.epam.brest.service.rest;

import com.epam.brest.entity.Employee;
import com.epam.brest.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

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
        return null;
    }

    @Override
    public Optional<Employee> findEmployeeById(Long id) {
        return Optional.empty();
    }

    @Override
    public Employee createEmployee(Employee employee) {
        return null;
    }

    @Override
    public Employee updateEmployee(Employee employee) {
        return null;
    }

    @Override
    public void deleteEmployeeById(Long id) {

    }
}
