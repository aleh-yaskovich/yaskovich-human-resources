package com.epam.brest.web_app.controllers;

import com.epam.brest.entity.Employee;
import com.epam.brest.service.DepartmentDtoService;
import com.epam.brest.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Controller
public class EmployeeController {

    private final static Logger LOGGER = LoggerFactory.getLogger(EmployeeController.class);

    private final EmployeeService employeeService;

    private final DepartmentDtoService departmentDtoService;

    public EmployeeController(EmployeeService employeeService, DepartmentDtoService departmentDtoService) {
        this.employeeService = employeeService;
        this.departmentDtoService = departmentDtoService;
    }

    @GetMapping("/employees")
    public String showAllEmployees(Model model) {
        LOGGER.debug("showAllEmployees()");
        model.addAttribute("employees", employeeService.findAllEmployees());
        return "employees";
    }

    @GetMapping("/employee")
    public String showEmployeeCreatePage(Model model) {
        LOGGER.debug("showEmployeeCreatePage({})", model);
        model.addAttribute("isNew", true);
        model.addAttribute("employee", new Employee());
        model.addAttribute("departments", departmentDtoService.findAllDepartments());
        return "employee";
    }

    @PostMapping(value = "/employee")
    public String createEmployee(Employee employee) {
        LOGGER.debug("createEmployee({})", employee);
        employeeService.createEmployee(employee);
        return "redirect:/employees";
    }

    @GetMapping("/employee/{id}")
    public String showEmployeeUpdatePage(@PathVariable Long id, Model model) {
        LOGGER.debug("showEmployeeUpdatePage({},{})", id, model);
        Optional<Employee> optionalEmployee = employeeService.findEmployeeById(id);
        if(optionalEmployee.isPresent()) {
            model.addAttribute("isNew", false);
            model.addAttribute("employee", optionalEmployee.get());
            model.addAttribute("departments", departmentDtoService.findAllDepartments());
            return "employee";
        } else {
            return "redirect:/employees";
        }
    }

    @PostMapping(value = "/employee/{id}")
    public String updateEmployee(Employee employee) {
        LOGGER.debug("updateEmployee({})", employee);
        employeeService.updateEmployee(employee);
        return "redirect:/employees";
    }

    @GetMapping(value = "/employee/{id}/delete")
    public final String deleteEmployeeById(@PathVariable Long id, Model model) {
        LOGGER.debug("deleteEmployeeById({},{})", id, model);
        employeeService.deleteEmployeeById(id);
        return "redirect:/employees";
    }
}
