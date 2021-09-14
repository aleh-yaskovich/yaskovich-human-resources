package com.epam.brest.repository_jpa;

import com.epam.brest.entity_jpa.Department;
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
class DepartmentJpaRepositoryTest {

    @Autowired
    DepartmentJpaRepository repository;

    private List<Department> departments = Arrays.asList(
            new Department("IT"),
            new Department("MANAGEMENT"),
            new Department("SECURITY")
    );

    @Test
    void findAllTest() {
        repository.saveAll(departments);
        List<Department> departmentList = repository.findAll();
        assertNotNull(departmentList);
        assertTrue(departmentList.size() > 0);
    }

    @Test
    void findAllNamesTest() {
        repository.saveAll(departments);
        List<Department> departmentList = repository.findAll();
        assertTrue(departmentList.size() > 0);

        List<DepartmentName> departmentNames = repository.findAllBy();
        assertNotNull(departmentNames);
        assertTrue(departmentNames.size() > 0);
    }

    @Test
    void findAllNameTest() {
        repository.saveAll(departments);
        List<Department> departmentList = repository.findAll();
        assertTrue(departmentList.size() > 0);

        List<String> departmentNames = repository.findAllName();
        assertNotNull(departmentNames);
        assertTrue(departmentNames.size() > 0);
    }

    @Test
    void findByIdTest() {
        repository.saveAll(departments);
        List<Department> departmentList = repository.findAll();
        assertTrue(departmentList.size() > 0);
        Department department = departmentList.get(0);
        Long departmentId = department.getId();

        Optional<Department> departmentOptional = repository.findById(departmentId);
        Department departmentTest = departmentOptional.get();
        assertEquals(department, departmentTest);
    }

    @Test
    void findByIdWithWrongValue() {
        repository.saveAll(departments);
        List<Department> departmentList = repository.findAll();
        assertTrue(departmentList.size() > 0);

        Exception exception = assertThrows(NoSuchElementException.class, () -> {
            repository.findById(99L).get();
        });
        String expectedMessage = "No value present";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void findByNameTest() {
        repository.saveAll(departments);
        List<Department> departmentList = repository.findAll();
        assertTrue(departmentList.size() > 0);
        String departmentName = departmentList.get(0).getName();

        Optional<Department> departmentOptional = repository.findByName(departmentName);
        assertTrue(departmentOptional.isPresent());
        assertEquals(departmentOptional.get(), departmentList.get(0));
    }

    @Test
    void createNewDepartmentTest() {
        repository.saveAll(departments);
        List<Department> departmentList = repository.findAll();
        assertTrue(departmentList.size() > 0);

        int sizeBefore = departmentList.size();
        Department newDepartment = new Department("TEST");
        repository.save(newDepartment);

        departmentList = repository.findAll();
        int sizeAfter = departmentList.size();
        assertTrue(sizeBefore == (sizeAfter - 1));
        assertEquals(newDepartment.getName(), departmentList.get(sizeAfter-1).getName());
    }

//    @Test
//    void createDepartmentWithNotUniqueNameTest() {
//        repository.saveAll(departments);
//        List<Department> departmentList = repository.findAll();
//        assertTrue(departmentList.size() > 0);
//
//        int sizeBefore = departmentList.size();
//        String notUniqueName = departmentList.get(0).getName();
//        Department newDepartment = new Department(notUniqueName);
//
//        repository.save(newDepartment);
//        departmentList = repository.findAll();
//        int sizeAfter = departmentList.size();
//        assertTrue(sizeBefore == sizeAfter);
//    }

    @Test
    void updateDepartmentTest() {
        repository.saveAll(departments);
        List<Department> departmentList = repository.findAll();
        assertTrue(departmentList.size() > 0);

        Department department = departmentList.get(0);
        department.setName("UPDATE");
        repository.save(department);

        departmentList = repository.findAll();
        Department departmentTest = departmentList.get(0);
        assertEquals(departmentTest.getName(), "UPDATE");
        assertEquals(department, departmentTest);
    }

    @Test
    void deleteDepartmentTest() {
        repository.saveAll(departments);
        List<Department> departmentList = repository.findAll();
        assertTrue(departmentList.size() > 0);

        int sizeBefore = departmentList.size();
        repository.save(new Department("TEST_DELETE"));
        departmentList = repository.findAll();
        int sizeAfter = departmentList.size();
        assertTrue(sizeBefore == (sizeAfter - 1));

        Department departmentDel = departmentList.get(sizeAfter-1);
        assertEquals(departmentDel.getName(), "TEST_DELETE");
        repository.delete(departmentDel);

        departmentList = repository.findAll();
        int sizeAfterDeleting = departmentList.size();
        assertTrue(sizeAfterDeleting == (sizeAfter - 1));

        int res = 0;
        for(Department d : departmentList) {
            if(d.getName().equals("TEST_DELETE"))
                res++;
        }
        assertTrue(res == 0);
    }
}