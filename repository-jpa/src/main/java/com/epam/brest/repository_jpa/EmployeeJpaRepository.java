package com.epam.brest.repository_jpa;

import com.epam.brest.entity_jpa.Employee;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeJpaRepository extends CrudRepository<Employee, Long> {

    List<Employee> findAll();

    @Query("select e from Employee e where e.firstName = ?1")
    List<Employee> findByFirstName(String firstName);

    @Query("select e from Employee e where e.email = :email")
    Optional<Employee> findByEmail(@Param("email") String email);

    List<EmployeeName> findAllBy();
}
