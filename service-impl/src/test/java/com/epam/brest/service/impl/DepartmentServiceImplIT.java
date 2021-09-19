package com.epam.brest.service.impl;

import com.epam.brest.entity.Department;
import com.epam.brest.entity.DepartmentDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class DepartmentServiceImplIT {

    @Autowired
    DepartmentServiceImpl departmentService;

    @Autowired
    DepartmentDtoServiceImpl departmentDtoService;

    @Test
    void findAllDepartmentsTest() {
        List<DepartmentDto> departmentDtos = departmentDtoService.findAllDepartments();
        assertNotNull(departmentDtos);
        assertTrue(departmentDtos.isEmpty());
    }

    @Test
    void findDepartmentByIdTest() {
        Optional<Department> optional = departmentService.findDepartmentById(1L);
        assertNotNull(optional);
    }

    @Test
    void createDepartmentTest() {
        Department department = departmentService.createDepartment(new Department("TEST"));
        List<DepartmentDto> departmentDtos = departmentDtoService.findAllDepartments();
        assertTrue(departmentDtos.size() == 1);
        assertEquals(departmentDtos.get(0).getName(), "TEST");
        departmentService.deleteDepartmentById(departmentDtos.get(0).getId());
    }

    @Test
    void updateDepartmentTest() {
        Department department = departmentService.createDepartment(new Department("New Department"));
        List<DepartmentDto> departmentDtos = departmentDtoService.findAllDepartments();
        assertTrue(departmentDtos.size() == 1);
        Department departmentTest = departmentService.findDepartmentById(departmentDtos.get(0).getId()).get();
        departmentTest.setName("Updated Department");
        departmentService.updateDepartment(departmentTest);
        departmentDtos = departmentDtoService.findAllDepartments();
        assertTrue(departmentDtos.size() == 1);
        assertTrue(department.getId() == departmentDtos.get(0).getId());
        assertEquals(departmentDtos.get(0).getName(), "Updated Department");
        departmentService.deleteDepartmentById(departmentDtos.get(0).getId());
    }

    @Test
    void deleteDepartmentTest() {
        Department department = departmentService.createDepartment(new Department("New Department"));
        List<DepartmentDto> departmentDtos = departmentDtoService.findAllDepartments();
        assertTrue(departmentDtos.size() == 1);
        departmentService.deleteDepartmentById(department.getId());
        departmentDtos = departmentDtoService.findAllDepartments();
        assertTrue(departmentDtos.isEmpty());
    }
}
