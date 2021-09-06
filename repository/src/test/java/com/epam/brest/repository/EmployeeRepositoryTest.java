package com.epam.brest.repository;

import com.epam.brest.entity.Employee;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJdbcTest
class EmployeeRepositoryTest {

    @Autowired
    EmployeeRepository repository;

    @Test
    void findAllTest() {
        List<Employee> employees = repository.findAll();
        assertNotNull(employees);
        assertTrue(employees.size() > 0);
    }

    @Test
    void findByIdTest() {
        List<Employee> employees = repository.findAll();
        assertTrue(employees.size() > 0);

        Employee employee = employees.get(0);
        Employee testEmployee = repository.findById(employee.getEmployeeId()).get();
        assertEquals(employee, testEmployee);
    }

    @Test
    void saveEmployeeTest() {
        List<Employee> employees = repository.findAll();
        assertTrue(employees.size() > 0);

        Employee employee = new Employee("TestFirstName", "TestLastName", "test@mail.com", 500, 3);
        repository.save(employee);

        List<Employee> testEmployees = repository.findAll();
        assertTrue(employees.size() == (testEmployees.size()-1));

        Employee testEmployee = testEmployees.get(testEmployees.size()-1);
        assertEquals(testEmployee.getEmployeeFirstName(), "TestFirstName");
        assertEquals(employee, testEmployee);
    }

    @Test
    void updateEmployeeTest() {
        List<Employee> employees = repository.findAll();
        assertTrue(employees.size() > 0);

        Employee employee = employees.get(0);
        employee.setEmployeeFirstName("TEST");
        repository.save(employee);

        List<Employee> testEmployees = repository.findAll();
        Employee testEmployee = testEmployees.get(0);

        assertTrue(employees.size() == testEmployees.size());
        assertEquals(testEmployee.getEmployeeFirstName(), "TEST");
    }

    @Test
    void deleteEmployeeTest() {
        List<Employee> employees = repository.findAll();
        assertTrue(employees.size() > 0);

        Employee employee = new Employee("TestFirstName", "TestLastName", "test@mail.com", 500, 3);
        repository.save(employee);

        List<Employee> updateEmployees = repository.findAll();
        assertTrue(employees.size() == (updateEmployees.size()-1));

        Employee testEmployee = updateEmployees.get(updateEmployees.size()-1);
        repository.delete(testEmployee);

        List<Employee> resultEmployees = repository.findAll();
        assertTrue(resultEmployees.size() == (updateEmployees.size()-1));
    }
}