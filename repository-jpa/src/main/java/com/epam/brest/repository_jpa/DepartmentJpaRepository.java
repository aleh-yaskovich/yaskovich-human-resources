package com.epam.brest.repository_jpa;

import com.epam.brest.entity_jpa.Department;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DepartmentJpaRepository extends CrudRepository<Department, Long> {

    List<Department> findAll();

    @Query("select d from Department d where d.name = ?1")
    Optional<Department> findByName(String name);

    List<DepartmentName> findAllBy();

    @Query("select d.name from Department d")
    List<String> findAllName();
}
