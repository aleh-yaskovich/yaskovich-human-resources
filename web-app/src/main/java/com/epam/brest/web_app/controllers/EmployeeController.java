package com.epam.brest.web_app.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EmployeeController {

    private final static Logger LOGGER = LoggerFactory.getLogger(EmployeeController.class);

    @GetMapping("/employees")
    public String showAllEmployees(Model model) {
        LOGGER.debug("showAllEmployees()");
        return "employees";
    }

    @GetMapping("/employee")
    public String createEmployee(Model model) {
        LOGGER.debug("createEmployee()");
        return "employee";
    }
}
