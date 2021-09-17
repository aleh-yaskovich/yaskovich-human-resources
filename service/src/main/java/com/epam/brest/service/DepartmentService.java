package com.epam.brest.service;

import com.epam.brest.entity.Department;

import java.util.Optional;

public interface DepartmentService {

    Optional<Department> findDepartmentById(Long id);

    Department createDepartment(Department department);

    Department updateDepartment(Department department);

    void deleteDepartmentById(Long id);
}
