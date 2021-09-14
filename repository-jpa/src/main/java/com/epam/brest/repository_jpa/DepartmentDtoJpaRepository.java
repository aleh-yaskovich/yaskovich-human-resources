package com.epam.brest.repository_jpa;

import com.epam.brest.entity_jpa.DepartmentDto;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentDtoJpaRepository extends CrudRepository<DepartmentDto, Long> {

    @Query(value = "SELECT D.ID, D.NAME, AVG(E.SALARY) AS AVG_SALARY FROM DEPARTMENT D " +
            "LEFT JOIN EMPLOYEE E ON D.ID = E.DEPARTMENT_ID GROUP BY D.ID, D.NAME", nativeQuery = true)
    List<DepartmentDto> findAll();
}
