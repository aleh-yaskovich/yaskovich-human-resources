package com.epam.brest.rest_app.controllers;

import com.epam.brest.entity.Employee;
import com.epam.brest.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;

@RestController
public class EmployeeController {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeController.class);

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping(value = "/employees")
    public Collection<Employee> employees() {
        LOGGER.debug("employees()");
        return employeeService.findAllEmployees();
    }

    @GetMapping(value = "/employees/{id}")
    public ResponseEntity<Employee> findById(@PathVariable Long id) {
        LOGGER.debug("findById({})", id);
        Optional<Employee> optional = employeeService.findEmployeeById(id);
        return optional.isPresent()
                ? new ResponseEntity<>(optional.get(), HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

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
