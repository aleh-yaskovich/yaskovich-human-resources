package com.epam.brest.web_app.controllers;

import com.epam.brest.entity.Department;
import com.epam.brest.service.DepartmentDtoService;
import com.epam.brest.service.DepartmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Controller
public class DepartmentController {

    private final static Logger LOGGER = LoggerFactory.getLogger(DepartmentController.class);

    private final DepartmentService departmentService;
    private final DepartmentDtoService departmentDtoService;

    public DepartmentController(DepartmentService departmentService, DepartmentDtoService departmentDtoService) {
        this.departmentService = departmentService;
        this.departmentDtoService = departmentDtoService;
    }

    /**
    * Show page with all departments
    **/
    @GetMapping("/departments")
    public String showAllDepartments(Model model) {
        LOGGER.debug("showAllDepartments({})", model);
        model.addAttribute("departments", departmentDtoService.findAllDepartments());
        return "departments";
    }

    /**
     * Shows a page for creating a department
     **/
    @GetMapping("/department")
    public String showDepartmentCreatePage(Model model) {
        LOGGER.debug("showDepartmentCreatePage({})", model);
        model.addAttribute("isNew", true);
        model.addAttribute("department", new Department());
        return "department";
    }

    /**
     * Create new department
     **/
    @PostMapping(value = "/department")
    public String createDepartment(Department department) {
        LOGGER.debug("createDepartment({})", department);
        departmentService.createDepartment(department);
        return "redirect:/departments";
    }

    /**
     * Shows a page for updating a department
     **/
    @GetMapping("/department/{id}")
    public String showDepartmentUpdatePage(@PathVariable Long id, Model model) {
        LOGGER.debug("showDepartmentUpdatePage({},{})", id, model);
        Optional<Department> optionalDepartment = departmentService.findDepartmentById(id);
        if(optionalDepartment.isPresent()) {
            model.addAttribute("isNew", false);
            model.addAttribute("department", optionalDepartment.get());
            return "department";
        } else {
            return "redirect:/departments";
        }
    }

    /**
     * Update selected department
     **/
    @PostMapping(value = "/department/{id}")
    public String updateDepartment(Department department) {
        LOGGER.debug("updateDepartment({})", department);
        departmentService.updateDepartment(department);
        return "redirect:/departments";
    }

    /**
     * Delete selected department by ID
     **/
    @GetMapping(value = "/department/{id}/delete")
    public final String deleteDepartmentById(@PathVariable Long id, Model model) {
        LOGGER.debug("deleteDepartmentById({},{})", id, model);
        departmentService.deleteDepartmentById(id);
        return "redirect:/departments";
    }
}
