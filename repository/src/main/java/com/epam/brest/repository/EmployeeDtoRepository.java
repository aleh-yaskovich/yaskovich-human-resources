package com.epam.brest.repository;

import com.epam.brest.entity.EmployeeDto;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeDtoRepository extends CrudRepository<EmployeeDto, Long> {

    /**
     * Return all employees with department names
     **/
    @Query(value = "SELECT e.id, e.first_name, e.last_name, e.email, e.salary, d.name AS department_name " +
            "FROM employee e LEFT JOIN department d ON e.department_id = d.id GROUP BY e.id", nativeQuery = true)
    List<EmployeeDto> findAll();
}
