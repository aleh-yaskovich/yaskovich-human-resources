package com.epam.brest.service;

import com.epam.brest.entity.Department;

import java.util.Optional;

public interface DepartmentService {

    /**
     * Return one department by ID
     **/
    Optional<Department> findDepartmentById(Long id);

    /**
     * Create new department
     **/
    Department createDepartment(Department department);

    /**
     * Update selected department
     **/
    Department updateDepartment(Department department);

    /**
     * Delete selected department by ID
     **/
    void deleteDepartmentById(Long id);
}
