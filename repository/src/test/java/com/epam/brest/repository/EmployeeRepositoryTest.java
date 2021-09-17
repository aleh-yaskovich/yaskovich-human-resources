package com.epam.brest.repository;

import com.epam.brest.entity.Employee;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class EmployeeRepositoryTest {

    @Autowired
    private EmployeeRepository repository;

    private List<Employee> employeeList = Arrays.asList(
            new Employee("FirstName1", "LastName1", "mail1@mail.com", 500, 1L),
            new Employee("FirstName2", "LastName2", "mail2@mail.com", 600, 1L),
            new Employee("FirstName3", "LastName3", "mail3@mail.com", 1000, 2L)
    );

    @Test
    void findAllTest() {
        repository.saveAll(employeeList);
        List<Employee> employees = repository.findAll();
        assertNotNull(employees);
        assertTrue(employees.size() > 0);
    }

    @Test
    void findByIdTest() {
        repository.saveAll(employeeList);
        List<Employee> employees = repository.findAll();
        assertTrue(employees.size() > 0);

        Long id = employees.get(0).getId();
        Optional<Employee> optionalEmployee = repository.findById(id);
        assertTrue(optionalEmployee.isPresent());
        assertEquals(optionalEmployee.get(), employees.get(0));
    }

    @Test
    void findByEmailTest() {
        repository.saveAll(employeeList);
        List<Employee> employees = repository.findAll();
        assertTrue(employees.size() > 0);

        String email = employees.get(0).getEmail();
        List<Employee> employeesByEmail = repository.findByEmail(email);
        assertNotNull(employeesByEmail);
        assertTrue(employeesByEmail.size() > 0);
    }

    @Test
    void createEmployeeTest() {
        repository.saveAll(employeeList);
        List<Employee> employees = repository.findAll();
        assertTrue(employees.size() > 0);

        int sizeBefore = employees.size();
        Employee newEmployee = repository.save(new Employee("FirstName4", "LastName4", "mail4@mail.com", 900, 3L));
        employees = repository.findAll();
        int sizeAfter = employees.size();
        assertTrue(sizeBefore == (sizeAfter - 1));
        Employee testEmployee = employees.get(sizeAfter-1);
        assertEquals(newEmployee, testEmployee);
    }

    @Test
    void updateEmployeeTest() {
        repository.saveAll(employeeList);
        List<Employee> employees = repository.findAll();
        assertTrue(employees.size() > 0);

        Employee employee = employees.get(0);
        employee.setFirstName("Updated");
        repository.save(employee);

        employees = repository.findAll();
        Employee testEmployee = employees.get(0);
        assertEquals(employee, testEmployee);
    }

    @Test
    void deleteByIdTest() {
        repository.saveAll(employeeList);
        List<Employee> employees = repository.findAll();
        assertTrue(employees.size() > 0);

        Employee newEmployee = repository.save(new Employee("FirstName4", "LastName4", "mail4@mail.com", 900, 3L));
        employees = repository.findAll();
        int sizeBefore = employees.size();
        Employee employee = employees.get(sizeBefore-1);

        repository.deleteById(employee.getId());
        employees = repository.findAll();
        int sizeAfter = employees.size();
        assertTrue(sizeBefore == (sizeAfter + 1));

        boolean res = true;
        for(Employee e : employees) {
            if(e.getEmail().equals("mail4@mail.com"))
                res = false;
        }
        assertTrue(res);
    }
}