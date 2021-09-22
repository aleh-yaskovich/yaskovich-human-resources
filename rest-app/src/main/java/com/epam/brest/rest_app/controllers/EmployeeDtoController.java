package com.epam.brest.rest_app.controllers;

import com.epam.brest.entity.EmployeeDto;
import com.epam.brest.service.EmployeeDtoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@Tag(
        name="Employee-DTO controller",
        description="Show employees with department names"
)
public class EmployeeDtoController {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeDtoController.class);

    private final EmployeeDtoService employeeDtoService;

    public EmployeeDtoController(EmployeeDtoService employeeDtoService) {
        this.employeeDtoService = employeeDtoService;
    }

    /**
     * Return list of departments with average salaries
     **/
    @Operation(
            summary = "Return employees",
            description = "Return list of employees with department names"
    )
    @GetMapping(value = "/employees-dto")
    public Collection<EmployeeDto> employees() {
        LOGGER.debug("employees()");
        return employeeDtoService.findAllEmployees();
    }
}
