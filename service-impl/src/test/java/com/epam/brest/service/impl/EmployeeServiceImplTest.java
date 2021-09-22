package com.epam.brest.service.impl;

import com.epam.brest.entity.Employee;
import com.epam.brest.repository.EmployeeRepository;
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
class EmployeeServiceImplTest {

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    @Mock
    private EmployeeRepository repository;

    private List<Employee> employeeList = Arrays.asList(
            new Employee("FirstName1", "LastName1", "mail1@mail.com", 500, 1L),
            new Employee("FirstName2", "LastName2", "mail2@mail.com", 600, 1L),
            new Employee("FirstName3", "LastName3", "mail3@mail.com", 1000, 2L)
    );

    @Test
    void findAllEmployeesTest() {
        Mockito.when(repository.findAll()).thenReturn(employeeList);
        List<Employee> employees = employeeService.findAll();
        assertNotNull(employees);
        assertTrue(employees.size() > 0);
    }

    @Test
    void findEmployeeByIdTest() {
        Mockito.when(repository.findById(1L)).thenReturn(Optional.ofNullable(employeeList.get(0)));
        Optional<Employee> optionalEmployee = employeeService.findEmployeeById(1L);
        assertTrue(optionalEmployee.isPresent());
        assertEquals(optionalEmployee.get().getEmail(), employeeList.get(0).getEmail());
    }

    @Test
    void createEmployeeTest() {
        Employee employee = new Employee("FirstName3", "LastName3", "mail4@mail.com", 900, 3L);
        Mockito.when(repository.save(employee)).thenReturn(employee);
        Employee testEmployee = employeeService.createEmployee(employee);
        assertNotNull(testEmployee);
        assertEquals(employee.getEmail(), testEmployee.getEmail());
    }

    @Test
    void updateEmployeeTest() {
        Employee employee = new Employee("FirstName3", "LastName3", "mail4@mail.com", 900, 3L);
        Mockito.when(repository.save(employee)).thenReturn(employee);
        Employee testEmployee = employeeService.updateEmployee(employee);
        assertNotNull(testEmployee);
        assertEquals(employee.getEmail(), testEmployee.getEmail());
    }
}