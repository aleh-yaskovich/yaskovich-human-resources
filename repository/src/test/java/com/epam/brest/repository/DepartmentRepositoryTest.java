package com.epam.brest.repository;

import com.epam.brest.entity.Department;
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
class DepartmentRepositoryTest {

    @Autowired
    private DepartmentRepository repository;

    private List<Department> departmentList = Arrays.asList(
            new Department("IT"),
            new Department("MANAGEMENT"),
            new Department("SECURITY")
    );

    @Test
    void findByIdTest() {
        repository.saveAll(departmentList);
        List<Department> departments = repository.findAll();
        assertTrue(departments.size() > 0);

        Long id = departments.get(0).getId();
        Optional<Department> optionalDepartment = repository.findById(id);
        assertTrue(optionalDepartment.isPresent());
        assertEquals(optionalDepartment.get(), departments.get(0));
    }

    @Test
    void findByNameTest() {
        repository.saveAll(departmentList);
        List<Department> departments = repository.findAll();
        assertTrue(departments.size() > 0);

        String name = departments.get(0).getName();
        Optional<Department> optionalDepartment = repository.findByName(name);
        assertTrue(optionalDepartment.isPresent());
        assertEquals(optionalDepartment.get(), departments.get(0));
    }

    @Test
    void createDepartmentTest() {
        repository.saveAll(departmentList);
        List<Department> departments = repository.findAll();
        assertTrue(departments.size() > 0);

        int sizeBefore = departments.size();
        Department newDepartment = repository.save(new Department("New Department"));
        departments = repository.findAll();
        int sizeAfter = departments.size();
        assertTrue(sizeBefore == (sizeAfter - 1));
        Department testDepartment = departments.get(sizeAfter-1);
        assertEquals(testDepartment, newDepartment);
    }

    @Test
    void updateDepartmentTest() {
        repository.saveAll(departmentList);
        List<Department> departments = repository.findAll();
        assertTrue(departments.size() > 0);

        Department department = departments.get(0);
        department.setName("Updated Department");
        repository.save(department);

        departments = repository.findAll();
        Department testDepartment = departments.get(0);
        assertEquals(department, testDepartment);
    }

    @Test
    void deleteByIdTest() {
        repository.saveAll(departmentList);
        List<Department> departments = repository.findAll();
        assertTrue(departments.size() > 0);

        Department newDepartment = repository.save(new Department("New Department"));
        departments = repository.findAll();
        int sizeBefore = departments.size();
        Department department = departments.get(sizeBefore-1);

        repository.deleteById(department.getId());
        departments = repository.findAll();
        int sizeAfter = departments.size();
        assertTrue(sizeBefore == (sizeAfter + 1));

        boolean res = true;
        for(Department d : departments) {
            if(d.getName().equals("New Department"))
                res = false;
        }
        assertTrue(res);
    }
}