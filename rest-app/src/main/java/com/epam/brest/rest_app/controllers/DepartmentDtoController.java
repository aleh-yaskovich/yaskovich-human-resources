package com.epam.brest.rest_app.controllers;

import com.epam.brest.entity.DepartmentDto;
import com.epam.brest.service.DepartmentDtoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@Tag(
        name="Department-DTO controller",
        description="Show departments with average salary"
)
public class DepartmentDtoController {

    private static final Logger LOGGER = LoggerFactory.getLogger(DepartmentDtoController.class);

    private final DepartmentDtoService departmentDtoService;

    public DepartmentDtoController(DepartmentDtoService departmentDtoService) {
        this.departmentDtoService = departmentDtoService;
    }

    /**
    * Return list of departments with average salaries
    **/
    @Operation(
            summary = "Return departments",
            description = "Return list of departments with average salaries"
    )
    @GetMapping(value = "/departments-dto")
    public Collection<DepartmentDto> departments() {
        LOGGER.debug("departments()");
        return departmentDtoService.findAllDepartments();
    }
}
