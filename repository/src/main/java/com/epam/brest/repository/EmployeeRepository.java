package com.epam.brest.repository;

import com.epam.brest.entity.Employee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Long> {

    /**
     * Return all employees
     **/
    List<Employee> findAll();

    /**
     * Return one employee by ID
     **/
    Optional<Employee> findById(Long id);

    /**
     * Return one employee by email
     **/
    List<Employee> findByEmail(String email);

    /**
     * Create new employee or update selected employee
     **/
    Employee save(Employee employee);

    /**
     * Delete selected employee by ID
     **/
    void deleteById(Long id);
}
