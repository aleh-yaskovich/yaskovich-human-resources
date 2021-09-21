package com.epam.brest.service;

import com.epam.brest.entity.DepartmentDto;

import java.util.List;

public interface DepartmentDtoService {

    /**
     * Return all departments with average salary
     **/
    List<DepartmentDto> findAllDepartments();
}
