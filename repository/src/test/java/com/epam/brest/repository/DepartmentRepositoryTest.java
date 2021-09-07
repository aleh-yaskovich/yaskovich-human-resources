package com.epam.brest.repository;

import com.epam.brest.entity.Department;
import com.epam.brest.entity.DepartmentDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJdbcTest
class DepartmentRepositoryTest {

    @Autowired
    DepartmentRepository repository;

    @Test
    void findAllTest() {
        List<Department> departments = repository.findAll();
        assertNotNull(departments);
        assertTrue(departments.size() > 0);
    }

    @Test
    void findAllWithSalaryTest() {
        List<DepartmentDto> departments = repository.findAllWithSalary();
        assertNotNull(departments);
        assertTrue(departments.size() > 0);
    }

    @Test
    void findByIdTest() {
        Department department = repository.findById(1).get();
        assertTrue(department.getDepartmentId() == 1);
    }

    @Test
    void findByIdTestWithWrongParameter() {
        Exception exception = assertThrows(NoSuchElementException.class, () -> {
            repository.findById(99).get();
        });
        String expectedMessage = "No value present";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void saveDepartmentTest() {
        Department department = new Department("TEST");
        List<Department> departments = repository.findAll();
        assertTrue(departments.size() > 0);
        repository.save(department);
        List<Department> testDepartments = repository.findAll();
        assertTrue(departments.size() == (testDepartments.size()-1));
        Department testDepartment = testDepartments.get(testDepartments.size()-1);
        assertEquals(testDepartment.getDepartmentName(), "TEST");
        assertEquals(department, testDepartment);
    }

    @Test
    void updateDepartmentTest() {
        List<Department> departments = repository.findAll();
        assertTrue(departments.size() > 0);
        Department department = departments.get(0);
        department.setDepartmentName("UPDATE");
        repository.save(department);
        Department testDepartment = repository.findById(department.getDepartmentId()).get();
        assertEquals(testDepartment.getDepartmentName(), "UPDATE");
        assertEquals(department, testDepartment);
    }

    @Test
    void deleteDepartmentTest() {
        Department department = new Department("TEST");
        repository.save(department);
        List<Department> departments = repository.findAll();
        int size = departments.size();
        Department testDepartment = departments.get(size-1);
        repository.delete(testDepartment);
        departments = repository.findAll();
        assertTrue(departments.size() == (size-1));
    }
}