package com.epam.brest.entity;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.persistence.*;

@Entity
public class DepartmentDto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Schema(description = "Department-dto ID (equals department.id)")
    private Long id;

    @Schema(description = "Department-dto name (Not null, unique)")
    private String name;

    @Schema(description = "Department-dto average salary")
    private Integer avgSalary;

    @Schema(description = "Number of employees in the department")
    private Integer numberOfEmployees;

    public DepartmentDto() { }

    public DepartmentDto(String name, Integer avgSalary, Integer numberOfEmployees) {
        this.name = name;
        this.avgSalary = avgSalary;
        this.numberOfEmployees = numberOfEmployees;
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

    public Integer getNumberOfEmployees() {
        return numberOfEmployees;
    }

    public void setNumberOfEmployees(Integer numberOfEmployees) {
        this.numberOfEmployees = numberOfEmployees;
    }
}
