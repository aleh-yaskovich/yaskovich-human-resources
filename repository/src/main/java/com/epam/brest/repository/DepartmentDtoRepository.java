package com.epam.brest.repository;

import com.epam.brest.entity.DepartmentDto;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DepartmentDtoRepository extends CrudRepository<DepartmentDto, Long> {

    /**
    * Return all departments with average salary
    **/
    @Query(value = "SELECT D.ID, D.NAME, AVG(E.SALARY) AS AVG_SALARY FROM DEPARTMENT D " +
            "LEFT JOIN EMPLOYEE E ON D.ID = E.DEPARTMENT_ID GROUP BY D.ID, D.NAME", nativeQuery = true)
    List<DepartmentDto> findAll();
}
