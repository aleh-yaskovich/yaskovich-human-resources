package com.epam.brest.service.rest;

import com.epam.brest.entity.DepartmentDto;
import com.epam.brest.service.DepartmentDtoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class DepartmentDtoServiceRest implements DepartmentDtoService {

    private static final Logger LOGGER = LoggerFactory.getLogger(DepartmentDtoServiceRest.class);

    private String url = "http://localhost:8080/departments";
    private RestTemplate restTemplate;

    public DepartmentDtoServiceRest(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    /**
     * Return all departments with average salary
     **/
    @Override
    public List<DepartmentDto> findAllDepartments() {
        LOGGER.debug("findAllDepartments()");
        ResponseEntity responseEntity = restTemplate.getForEntity(url, List.class);
        return (List<DepartmentDto>) responseEntity.getBody();
    }
}
