package com.epam.brest.repository;

import com.epam.brest.entity.Department;
import com.epam.brest.entity.DepartmentDto;
import com.epam.brest.entity.Employee;
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
class DepartmentDtoRepositoryIT {

    @Autowired
    private DepartmentDtoRepository departmentDtoRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    private List<Department> departmentList = Arrays.asList(
            new Department("IT"),
            new Department("MANAGEMENT"),
            new Department("SECURITY")
    );

    private List<Employee> employeeList = Arrays.asList(
            new Employee("FirstName1", "LastName1", "mail1@mail.com", 500, 1L),
            new Employee("FirstName2", "LastName2", "mail2@mail.com", 600, 1L),
            new Employee("FirstName3", "LastName3", "mail3@mail.com", 1000, 2L)
    );

    @Test
    void findAllTest() {
        departmentRepository.saveAll(departmentList);
        employeeRepository.saveAll(employeeList);
        List<DepartmentDto> departments = departmentDtoRepository.findAll();
        assertNotNull(departments);
        assertTrue(departments.size() > 0);
    }
}