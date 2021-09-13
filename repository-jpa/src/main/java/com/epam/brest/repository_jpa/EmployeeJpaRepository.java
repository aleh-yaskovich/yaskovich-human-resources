package com.epam.brest.repository_jpa;

import com.epam.brest.entity_jpa.Employee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeJpaRepository extends CrudRepository<Employee, Long> {

    List<Employee> findAll();
}
