package com.epam.brest.entity_jpa;

import javax.persistence.*;
import java.util.Objects;

@Entity(name = "Employee")
@Table(name = "EMPLOYEE")
public class Employee {

    @Id
    @SequenceGenerator(
            name = "employee_sequence",
            sequenceName = "employee_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "employee_sequence"
    )
    @Column(
            name = "ID",
            updatable = false
    )
    private Long id;

    @Column(
            name = "FIRST_NAME",
            nullable = false,
            columnDefinition = "VARCHAR"
    )
    private String firstName;

    @Column(
            name = "LAST_NAME",
            nullable = false,
            columnDefinition = "VARCHAR"
    )
    private String lastName;

    @Column(
            name = "EMAIL",
            nullable = false,
            columnDefinition = "VARCHAR",
            unique = true
    )
    private String email;

    @Column(
            name = "SALARY",
            nullable = false
    )
    private Integer salary;

    @Column(
            name = "DEPARTMENT_ID",
            nullable = false
    )
    private Long departmentId;

    public Employee() {
    }

    public Employee(String firstName, String lastName, String email, Integer salary, Long departmentId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.salary = salary;
        this.departmentId = departmentId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Objects.equals(id, employee.id) && Objects.equals(firstName, employee.firstName) && Objects.equals(lastName, employee.lastName) && Objects.equals(email, employee.email) && Objects.equals(salary, employee.salary) && Objects.equals(departmentId, employee.departmentId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, email, salary, departmentId);
    }
}
