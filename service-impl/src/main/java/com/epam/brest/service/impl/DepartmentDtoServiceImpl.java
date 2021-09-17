package com.epam.brest.service.impl;

import com.epam.brest.entity.DepartmentDto;
import com.epam.brest.service.DepartmentDtoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class DepartmentDtoServiceImpl implements DepartmentDtoService {

    @Override
    public List<DepartmentDto> findAllDepartments() {
        return null;
    }
}
