package com.epam.brest.service;

import com.epam.brest.entity.EmployeeDto;

import java.util.List;

public interface EmployeeDtoService {

    /**
     * Return all employees with department names
     **/
    List<EmployeeDto> findAllEmployees();
}
