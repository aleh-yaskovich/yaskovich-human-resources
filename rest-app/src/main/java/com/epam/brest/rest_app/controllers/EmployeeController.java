package com.epam.brest.rest_app.controllers;

import com.epam.brest.entity.Employee;
import com.epam.brest.service.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;

@RestController
@Tag(
        name="Employee controller",
        description="Provides CRUD operations for employees"
)
public class EmployeeController {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeController.class);

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    /**
     * Return list of all employees
     **/
    @Operation(
            summary = "Return employees",
            description = "Return list of all employees"
    )
    @GetMapping(value = "/employees")
    public Collection<Employee> employees() {
        LOGGER.debug("employees()");
        return employeeService.findAllEmployees();
    }

    /**
    * Return selected employee by ID
    **/
    @Operation(
            summary = "Return one employee",
            description = "Return selected employee by ID"
    )
    @GetMapping(value = "/employees/{id}")
    public ResponseEntity<Employee> findById(@PathVariable Long id) {
        LOGGER.debug("findById({})", id);
        Optional<Employee> optional = employeeService.findEmployeeById(id);
        return optional.isPresent()
                ? new ResponseEntity<>(optional.get(), HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     * Create new employee
     **/
    @Operation(
            summary = "Create employee",
            description = "Create new employee"
    )
    @PostMapping(value = "/employees", consumes = {"application/json"}, produces = {"application/json"})
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {
        LOGGER.debug("createEmployee({})", employee);
        try{
            Employee result = employeeService.createEmployee(employee);
            return new ResponseEntity<>(result, HttpStatus.CREATED);
        } catch (IllegalArgumentException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        }
    }

    /**
     * Update selected employee
     **/
    @Operation(
            summary = "Update employee",
            description = "Update selected employee"
    )
    @PutMapping(value = "/employees", consumes = {"application/json"}, produces = {"application/json"})
    public ResponseEntity<Employee> updateEmployee(@RequestBody Employee employee) {
        LOGGER.debug("updateEmployee({})", employee);
        try{
            Employee result = employeeService.updateEmployee(employee);
            return new ResponseEntity<>(result, HttpStatus.CREATED);
        } catch (IllegalArgumentException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        }
    }

    /**
     * Delete selected employee by ID
     **/
    @Operation(
            summary = "Delete employee",
            description = "Delete selected employee by ID"
    )
    @DeleteMapping(value = "/employees/{id}", produces = {"application/json"})
    public ResponseEntity<String> deleteEmployee(@PathVariable Long id) {
        LOGGER.debug("deleteEmployee({})", id);
        try{
            employeeService.deleteEmployeeById(id);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (IllegalArgumentException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_MODIFIED);
        }
    }
}
