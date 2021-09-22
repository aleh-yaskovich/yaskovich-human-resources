package com.epam.brest.entity;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.persistence.*;

@Entity
public class EmployeeDto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Schema(description = "Employee-dto ID (equals employee.id)")
    private Long id;

    @Schema(description = "Employee first name (Not null)")
    private String firstName;

    @Schema(description = "Employee last name (Not null)")
    private String lastName;

    @Schema(description = "Employee email (Not null, unique)")
    private String email;

    @Schema(description = "Employee salary")
    private Integer salary;

    @Schema(description = "Department in which the employee works")
    private String departmentName;

    public EmployeeDto() { }

    public EmployeeDto(String firstName, String lastName, String email, Integer salary, String departmentName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.salary = salary;
        this.departmentName = departmentName;
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

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }
}
