package com.epam.brest.service.rest;

import com.epam.brest.entity.Department;
import com.epam.brest.service.DepartmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Optional;

@Service
public class DepartmentServiceRest implements DepartmentService {

    private static final Logger LOGGER = LoggerFactory.getLogger(DepartmentServiceRest.class);

    private String url = "http://localhost:8080/departments";
    private RestTemplate restTemplate;

    public DepartmentServiceRest(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    /**
     * Return one department by ID
     **/
    @Override
    public Optional<Department> findDepartmentById(Long id) {
        LOGGER.debug("findDepartmentById({})", id);
        ResponseEntity<Department> responseEntity =
                restTemplate.getForEntity(url + "/" + id, Department.class);
        return Optional.ofNullable(responseEntity.getBody());
    }

    /**
     * Create new department
     **/
    @Override
    public Department createDepartment(Department department) {
        LOGGER.debug("createDepartment({})", department);
        ResponseEntity responseEntity = restTemplate.postForEntity(url, department, Department.class);
        return (Department) responseEntity.getBody();
    }

    /**
     * Update selected department
     **/
    @Override
    public Department updateDepartment(Department department) {
        LOGGER.debug("updateDepartment({})", department);
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<Department> entity = new HttpEntity<>(department, headers);
        ResponseEntity<Department> result = restTemplate.exchange(url, HttpMethod.PUT, entity, Department.class);
        return (Department) result.getBody();
    }

    /**
     * Delete selected department by ID
     **/
    @Override
    public void deleteDepartmentById(Long id) {
        LOGGER.debug("deleteDepartmentById({})", id);
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<Department> entity = new HttpEntity<>(headers);
        ResponseEntity<Integer> result =
                restTemplate.exchange(url + "/" + id, HttpMethod.DELETE, entity, Integer.class);
    }
}
