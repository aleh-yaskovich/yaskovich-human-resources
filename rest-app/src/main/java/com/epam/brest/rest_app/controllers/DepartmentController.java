package com.epam.brest.rest_app.controllers;

import com.epam.brest.entity.Department;
import com.epam.brest.service.DepartmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class DepartmentController {

    private static final Logger LOGGER = LoggerFactory.getLogger(DepartmentController.class);

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping(value = "/departments/{id}")
    public ResponseEntity<Department> findById(@PathVariable Long id) {
        LOGGER.debug("findById({})", id);
        Optional<Department> optional = departmentService.findDepartmentById(id);
        return optional.isPresent()
                ? new ResponseEntity<>(optional.get(), HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping(value = "/departments", consumes = {"application/json"}, produces = {"application/json"})
    public ResponseEntity<Integer> createDepartment(@RequestBody Department department) {
        LOGGER.debug("createDepartment({})", department);
        departmentService.createDepartment(department);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping(value = "/departments", consumes = {"application/json"}, produces = {"application/json"})
    public ResponseEntity<Integer> updateDepartment(@RequestBody Department department) {
        LOGGER.debug("updateDepartment({})", department);
        departmentService.updateDepartment(department);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/departments/{id}", produces = {"application/json"})
    public ResponseEntity<Integer> deleteDepartment(@PathVariable Long id) {
        LOGGER.debug("deleteDepartment({})", id);
        departmentService.deleteDepartmentById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
