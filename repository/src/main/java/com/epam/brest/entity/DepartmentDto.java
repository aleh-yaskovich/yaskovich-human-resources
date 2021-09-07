package com.epam.brest.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Objects;

@Table("DEPARTMENT")
public class DepartmentDto {

    private int departmentId;
    private String departmentName;
    private int avgSalary;

    public DepartmentDto() {
    }

    public DepartmentDto(int departmentId, String departmentName, int avgSalary) {
        this.departmentId = departmentId;
        this.departmentName = departmentName;
        this.avgSalary = avgSalary;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public int getAvgSalary() {
        return avgSalary;
    }

    public void setAvgSalary(int avgSalary) {
        this.avgSalary = avgSalary;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DepartmentDto that = (DepartmentDto) o;
        return departmentId == that.departmentId && avgSalary == that.avgSalary && Objects.equals(departmentName, that.departmentName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(departmentId, departmentName, avgSalary);
    }
}
