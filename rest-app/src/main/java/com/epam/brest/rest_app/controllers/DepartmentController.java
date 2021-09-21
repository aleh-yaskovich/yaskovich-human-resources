package com.epam.brest.rest_app.controllers;

import com.epam.brest.entity.Department;
import com.epam.brest.service.DepartmentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@Tag(
        name="Department controller",
        description="Provides CRUD operations for departments"
)
public class DepartmentController {

    private static final Logger LOGGER = LoggerFactory.getLogger(DepartmentController.class);

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    /**
    * Return selected department by ID
    **/
    @Operation(
            summary = "Return one department",
            description = "Return selected department by ID"
    )
    @GetMapping(value = "/departments/{id}")
    public ResponseEntity<Department> findById(@PathVariable Long id) {
        LOGGER.debug("findById({})", id);
        Optional<Department> optional = departmentService.findDepartmentById(id);
        return optional.isPresent()
                ? new ResponseEntity<>(optional.get(), HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     * Create new department
     **/
    @Operation(
            summary = "Create department",
            description = "Create new department"
    )
    @PostMapping(value = "/departments", consumes = {"application/json"}, produces = {"application/json"})
    public ResponseEntity<Department> createDepartment(@RequestBody Department department) {
        LOGGER.debug("createDepartment({})", department);
        try{
            Department result = departmentService.createDepartment(department);
            return new ResponseEntity<>(result, HttpStatus.CREATED);
        } catch (IllegalArgumentException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        }
    }

    /**
     * Update selected department
     **/
    @Operation(
            summary = "Update department",
            description = "Update selected department"
    )
    @PutMapping(value = "/departments", consumes = {"application/json"}, produces = {"application/json"})
    public ResponseEntity<Department> updateDepartment(@RequestBody Department department) {
        LOGGER.debug("updateDepartment({})", department);
        try{
            Department result = departmentService.updateDepartment(department);
            return new ResponseEntity<>(result, HttpStatus.CREATED);
        } catch (IllegalArgumentException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        }
    }

    /**
     * Delete selected department by ID
     **/
    @Operation(
            summary = "Delete department",
            description = "Delete selected department by ID"
    )
    @DeleteMapping(value = "/departments/{id}", produces = {"application/json"})
    public ResponseEntity<String> deleteDepartment(@PathVariable Long id) {
        LOGGER.debug("deleteDepartment({})", id);
        try{
            departmentService.deleteDepartmentById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (IllegalArgumentException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_MODIFIED);
        }
    }
}
