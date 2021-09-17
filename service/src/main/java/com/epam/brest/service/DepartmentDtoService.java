package com.epam.brest.service;

import com.epam.brest.entity.DepartmentDto;

import java.util.List;

public interface DepartmentDtoService {

    List<DepartmentDto> findAllDepartments();
}
