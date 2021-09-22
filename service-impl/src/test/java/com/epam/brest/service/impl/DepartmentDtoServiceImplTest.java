package com.epam.brest.service.impl;

import com.epam.brest.entity.DepartmentDto;
import com.epam.brest.repository.DepartmentDtoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class DepartmentDtoServiceImplTest {

    @InjectMocks
    private DepartmentDtoServiceImpl departmentDtoService;

    @Mock
    private DepartmentDtoRepository departmentDtoRepository;

    private List<DepartmentDto> departmentDtoList = Arrays.asList(
            new DepartmentDto("IT", 100, 2),
            new DepartmentDto("MANAGEMENT", 200, 1),
            new DepartmentDto("SECURITY", 300, 0)
    );

    @Test
    void findAllDepartmentsTest() {
        Mockito.when(departmentDtoRepository.findAll()).thenReturn(departmentDtoList);
        List<DepartmentDto> departments = departmentDtoService.findAllDepartments();
        assertNotNull(departments);
        assertTrue(departments.size() > 0);
    }
}