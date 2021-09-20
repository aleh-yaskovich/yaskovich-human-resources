package com.epam.brest.rest_app.controllers;

import com.epam.brest.entity.DepartmentDto;
import com.epam.brest.service.DepartmentDtoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
public class DepartmentDtoController {

    private static final Logger LOGGER = LoggerFactory.getLogger(DepartmentDtoController.class);

    private final DepartmentDtoService departmentDtoService;

    public DepartmentDtoController(DepartmentDtoService departmentDtoService) {
        this.departmentDtoService = departmentDtoService;
    }

    @GetMapping(value = "/departments")
    public Collection<DepartmentDto> departments() {
        LOGGER.debug("departments()");
        return departmentDtoService.findAllDepartments();
    }
}
