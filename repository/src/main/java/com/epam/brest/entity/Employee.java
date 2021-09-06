package com.epam.brest.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;

import java.util.Objects;

public class Employee {

    @Id
    private int employeeId;

    @Column("FIRSTNAME")
    private String employeeFirstName;

    @Column("LASTNAME")
    private String employeeLastName;

    @Column("EMAIL")
    private String employeeEmail;

    @Column("SALARY")
    private int employeeSalary;

    private int departmentId;

    public Employee() {
    }

    public Employee(String employeeFirstName, String employeeLastName, String employeeEmail, int employeeSalary, int departmentId) {
        this.employeeFirstName = employeeFirstName;
        this.employeeLastName = employeeLastName;
        this.employeeEmail = employeeEmail;
        this.employeeSalary = employeeSalary;
        this.departmentId = departmentId;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeFirstName() {
        return employeeFirstName;
    }

    public void setEmployeeFirstName(String employeeFirstName) {
        this.employeeFirstName = employeeFirstName;
    }

    public String getEmployeeLastName() {
        return employeeLastName;
    }

    public void setEmployeeLastName(String employeeLastName) {
        this.employeeLastName = employeeLastName;
    }

    public String getEmployeeEmail() {
        return employeeEmail;
    }

    public void setEmployeeEmail(String employeeEmail) {
        this.employeeEmail = employeeEmail;
    }

    public int getEmployeeSalary() {
        return employeeSalary;
    }

    public void setEmployeeSalary(int employeeSalary) {
        this.employeeSalary = employeeSalary;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return employeeId == employee.employeeId && employeeSalary == employee.employeeSalary && departmentId == employee.departmentId && Objects.equals(employeeFirstName, employee.employeeFirstName) && Objects.equals(employeeLastName, employee.employeeLastName) && Objects.equals(employeeEmail, employee.employeeEmail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(employeeId, employeeFirstName, employeeLastName, employeeEmail, employeeSalary, departmentId);
    }
}
