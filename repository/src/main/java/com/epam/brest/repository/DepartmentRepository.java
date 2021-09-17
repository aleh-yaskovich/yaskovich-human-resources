package com.epam.brest.repository;

import com.epam.brest.entity.Department;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface DepartmentRepository extends CrudRepository<Department, Long> {

    List<Department> findAll();

    Optional<Department> findById(Long id);

    Optional<Department> findByName(String name);

    Department save(Department department);

    void deleteById(Long id);
}
