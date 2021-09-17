package com.epam.brest.service;

import com.epam.brest.entity.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {

    List<Employee> findAllEmployees();

    Optional<Employee> findEmployeeById(Long id);

    Employee createEmployee(Employee employee);

    Employee updateEmployee(Employee employee);

    void deleteEmployeeById(Long id);
}
