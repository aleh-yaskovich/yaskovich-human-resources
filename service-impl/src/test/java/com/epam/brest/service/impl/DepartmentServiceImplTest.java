package com.epam.brest.service.impl;

import com.epam.brest.entity.Department;
import com.epam.brest.repository.DepartmentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class DepartmentServiceImplTest {

    @InjectMocks
    private DepartmentServiceImpl departmentService;

    @Mock
    private DepartmentRepository departmentRepository;

    private List<Department> departmentList = Arrays.asList(
            new Department("IT"),
            new Department("MANAGEMENT"),
            new Department("SECURITY")
    );

    @Test
    void findDepartmentByIdTest() {
        Mockito.when(departmentRepository.findById(1L)).thenReturn(Optional.ofNullable(departmentList.get(0)));
        Optional<Department> optionalDepartment = departmentService.findDepartmentById(1L);
        assertNotNull(optionalDepartment);
        assertEquals(optionalDepartment.get().getName(), departmentList.get(0).getName());
    }

    @Test
    void createDepartmentTest() {
        Department department = new Department("TEST");
        Mockito.when(departmentRepository.save(department)).thenReturn(department);
        Department testDepartment = departmentService.createDepartment(department);
        assertNotNull(testDepartment);
        assertEquals(department.getName(), testDepartment.getName());
    }

    @Test
    void updateDepartmentTest() {
        Department department = new Department("TEST");
        Mockito.when(departmentRepository.save(department)).thenReturn(department);
        Department testDepartment = departmentService.updateDepartment(department);
        assertNotNull(testDepartment);
        assertEquals(department.getName(), testDepartment.getName());
    }
}