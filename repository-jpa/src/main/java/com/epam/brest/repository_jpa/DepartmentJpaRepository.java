package com.epam.brest.repository_jpa;

import com.epam.brest.entity_jpa.Department;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentJpaRepository extends CrudRepository<Department, Long> {

    List<Department> findAll();
}
