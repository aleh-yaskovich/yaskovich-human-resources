package com.epam.brest.entity;

import javax.persistence.*;

@Entity
@Table(name = "DEPARTMENT")
public class DepartmentDto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
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
