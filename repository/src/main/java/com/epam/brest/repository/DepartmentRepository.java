package com.epam.brest.repository;

import com.epam.brest.entity.Department;
import com.epam.brest.entity.DepartmentDto;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentRepository extends CrudRepository<Department, Integer> {

    List<Department> findAll();

    @Query(value="SELECT D.DEPARTMENT_ID, D.DEPARTMENT_NAME, AVG(E.SALARY) AS AVG_SALARY FROM DEPARTMENT D " +
            "LEFT JOIN EMPLOYEE E ON D.DEPARTMENT_ID = E.DEPARTMENT_ID GROUP BY D.DEPARTMENT_ID, D.DEPARTMENT_NAME",
            rowMapperClass = DepartmentDtoMapper.class)
    List<DepartmentDto> findAllWithSalary();
}
