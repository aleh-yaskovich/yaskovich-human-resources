package com.epam.brest.entity;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.persistence.*;

@Entity
@Table(name = "DEPARTMENT")
public class DepartmentDto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Schema(description = "Department-dto ID (equals department.id)")
    private Long id;

    @Schema(description = "Department-dto name (Not null, unique)")
    private String name;

    @Schema(description = "Department-dto average salary")
    private Integer avgSalary;

    public DepartmentDto() { }

    public DepartmentDto(String name, Integer avgSalary) {
        this.name = name;
        this.avgSalary = avgSalary;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAvgSalary() {
        return avgSalary;
    }

    public void setAvgSalary(Integer avgSalary) {
        this.avgSalary = avgSalary;
    }
}
