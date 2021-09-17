package com.epam.brest.repository;

import com.epam.brest.entity.Employee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Long> {

    List<Employee> findAll();

    Optional<Employee> findById(Long id);

    Optional<Employee> findByEmail(String email);

    Employee save(Employee employee);

    void deleteById(Long id);
}
