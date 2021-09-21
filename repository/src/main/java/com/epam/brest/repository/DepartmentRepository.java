package com.epam.brest.repository;

import com.epam.brest.entity.Department;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface DepartmentRepository extends CrudRepository<Department, Long> {

    /**
     * Return all departments
     **/
    List<Department> findAll();

    /**
     * Return one department by ID
     **/
    Optional<Department> findById(Long id);

    /**
     * Return one department by name
     **/
    List<Department> findByName(String name);

    /**
     * Create new department or update selected department
     **/
    Department save(Department department);

    /**
     * Delete selected department by ID
     **/
    void deleteById(Long id);
}
