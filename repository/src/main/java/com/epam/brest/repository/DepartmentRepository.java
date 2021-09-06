package com.epam.brest.repository;

import com.epam.brest.entity.Department;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DepartmentRepository extends CrudRepository<Department, Integer> {

    List<Department> findAll();

    @Query("SELECT * FROM DEPARTMENT WHERE DEPARTMENT_ID = :ID")
    Optional<Department> findById(@Param("ID") long id);

    Department save(Department department);

    @Modifying
    @Query("UPDATE DEPARTMENT SET DEPARTMENT_NAME = :name WHERE DEPARTMENT_ID = :id")
    boolean updateById(@Param("id") Integer id, @Param("name") String name);

    void delete(Department department);
}
