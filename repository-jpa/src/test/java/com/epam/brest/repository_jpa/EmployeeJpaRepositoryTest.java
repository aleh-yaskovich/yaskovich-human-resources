package com.epam.brest.repository_jpa;

import com.epam.brest.entity_jpa.Employee;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class EmployeeJpaRepositoryTest {

    @Autowired
    EmployeeJpaRepository repository;

    private List<Employee> employees = Arrays.asList(
            new Employee("FirstName1", "LastName1", "mail1@mail.com", 500, 1L),
            new Employee("FirstName2", "LastName2", "mail2@mail.com", 600, 1L),
            new Employee("FirstName3", "LastName3", "mail3@mail.com", 1000, 2L)
    );

    @Test
    void findAllTest() {
        repository.saveAll(employees);
        List<Employee> employeeList = repository.findAll();
        assertNotNull(employeeList);
        assertTrue(employeeList.size() > 0);
    }

    @Test
    void findByIdTest() {
        repository.saveAll(employees);
        List<Employee> employeeList = repository.findAll();
        assertTrue(employeeList.size() > 0);
        Employee employee = employeeList.get(0);
        Long employeeId = employee.getId();

        Optional<Employee> optionalEmployee = repository.findById(employeeId);
        Employee employeeTest = optionalEmployee.get();
        assertEquals(employee, employeeTest);
    }

    @Test
    void findByIdWithWrongValueTest() {
        repository.saveAll(employees);
        List<Employee> employeeList = repository.findAll();
        assertTrue(employeeList.size() > 0);

        Exception exception = assertThrows(NoSuchElementException.class, () -> {
            repository.findById(99L).get();
        });
        String expectedMessage = "No value present";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void createNewEmployeeTest() {
        repository.saveAll(employees);
        List<Employee> employeeList = repository.findAll();
        assertTrue(employeeList.size() > 0);

        int sizeBefore = employeeList.size();
        Employee employee =  new Employee("FirstName4", "LastName4", "mail4@mail.com", 750, 3L);
        repository.save(employee);

        employeeList = repository.findAll();
        int sizeAfter = employeeList.size();
        assertTrue(sizeBefore == (sizeAfter - 1));
        Employee employeeTest = employeeList.get(sizeAfter-1);
        assertEquals(employeeTest.getFirstName(), "FirstName4");
        assertEquals(employeeTest.getEmail(), "mail4@mail.com");
    }

    @Test
    void updateEmployeeTest() {
        repository.saveAll(employees);
        List<Employee> employeeList = repository.findAll();
        assertTrue(employeeList.size() > 0);

        Employee employee = employeeList.get(0);
        employee.setFirstName("TEST");
        repository.save(employee);

        employeeList = repository.findAll();
        Employee employeeTest = employeeList.get(0);
        assertEquals(employee, employeeTest);
        assertEquals(employeeTest.getFirstName(), "TEST");
    }

    @Test
    void deleteEmployeeTest() {
        repository.saveAll(employees);
        List<Employee> employeeList = repository.findAll();
        assertTrue(employeeList.size() > 0);

        int sizeBefore = employeeList.size();
        Employee employee =  new Employee("FirstName4", "LastName4", "mail4@mail.com", 750, 3L);
        repository.save(employee);

        employeeList = repository.findAll();
        int sizeAfter = employeeList.size();
        Employee employeeTest = employeeList.get(sizeAfter-1);
        repository.delete(employeeTest);

        employeeList = repository.findAll();
        int sizeAfterDeleting = employeeList.size();
        assertTrue(sizeAfterDeleting == (sizeAfter - 1));
        int res = 0;
        for(Employee e : employeeList) {
            if(e.getEmail().equals("mail4@mail.com"))
                res++;
        }
        assertTrue(res == 0);
    }
}