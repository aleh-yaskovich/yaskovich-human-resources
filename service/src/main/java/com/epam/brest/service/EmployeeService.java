package com.epam.brest.service;

import com.epam.brest.entity.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {

    /**
     * Return all employees
     **/
    List<Employee> findAllEmployees();

    /**
     * Return one employee by ID
     **/
    Optional<Employee> findEmployeeById(Long id);

    /**
     * Create new employee
     **/
    Employee createEmployee(Employee employee);

    /**
     * Update selected employee
     **/
    Employee updateEmployee(Employee employee);

    /**
     * Delete employee by ID
     **/
    void deleteEmployeeById(Long id);
}
