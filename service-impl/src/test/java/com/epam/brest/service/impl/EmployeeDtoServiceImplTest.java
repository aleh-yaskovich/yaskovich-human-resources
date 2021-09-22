package com.epam.brest.service.impl;

import com.epam.brest.entity.EmployeeDto;
import com.epam.brest.repository.EmployeeDtoRepository;
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
class EmployeeDtoServiceImplTest {

    @InjectMocks
    private EmployeeDtoServiceImpl employeeDtoService;

    @Mock
    private EmployeeDtoRepository employeeDtoRepository;

    private List<EmployeeDto> employeeDtoList = Arrays.asList(
            new EmployeeDto("FirstName1", "LastName1", "mail1@mail.com", 500, "IT"),
            new EmployeeDto("FirstName2", "LastName2", "mail2@mail.com", 600, "IT"),
            new EmployeeDto("FirstName3", "LastName3", "mail3@mail.com", 1000, "MANAGEMENT")
    );

    @Test
    void findAllEmployeesTest() {
        Mockito.when(employeeDtoRepository.findAll()).thenReturn(employeeDtoList);
        List<EmployeeDto> employees = employeeDtoService.findAllEmployees();
        assertNotNull(employees);
        assertTrue(employees.size() > 0);
    }
}