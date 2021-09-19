package com.epam.brest.web_app.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DepartmentController {

    private final static Logger LOGGER = LoggerFactory.getLogger(DepartmentController.class);

    @GetMapping("/departments")
    public String showAllDepartments(Model model) {
        LOGGER.debug("showAllDepartments()");
        return "departments";
    }

    @GetMapping("/department")
    public String createDepartment(Model model) {
        LOGGER.debug("createDepartment()");
        return "department";
    }

}
