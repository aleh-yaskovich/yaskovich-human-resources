package com.epam.brest.repository_jpa;

import com.epam.brest.entity_jpa.Department;
import com.epam.brest.entity_jpa.DepartmentDto;
import com.epam.brest.entity_jpa.Employee;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class DepartmentDtoJpaRepositoryTest {

    @Autowired
    DepartmentDtoJpaRepository departmentDtoJpaRepository;

    @Autowired
    DepartmentJpaRepository departmentJpaRepository;

    @Autowired
    EmployeeJpaRepository employeeJpaRepository;

    private List<Department> departments = Arrays.asList(
            new Department("IT"),
            new Department("MANAGEMENT"),
            new Department("SECURITY")
    );

    private List<Employee> employees = Arrays.asList(
            new Employee("FirstName1", "LastName1", "mail1@mail.com", 500, 1L),
            new Employee("FirstName2", "LastName2", "mail2@mail.com", 600, 1L),
            new Employee("FirstName3", "LastName3", "mail3@mail.com", 1000, 2L)
    );

    @Test
    void findAllTest() {
        departmentJpaRepository.saveAll(departments);
        employeeJpaRepository.saveAll(employees);

        List<DepartmentDto> departmentDtos = departmentDtoJpaRepository.findAll();
        assertNotNull(departmentDtos);
        assertTrue(departmentDtos.size() > 0);
    }
}