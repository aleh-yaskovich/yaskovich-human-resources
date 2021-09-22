package com.epam.brest.web_app.controllers;

import com.epam.brest.entity.Department;
import com.epam.brest.entity.DepartmentDto;
import com.epam.brest.service.DepartmentDtoService;
import com.epam.brest.service.DepartmentService;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.isA;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@WebMvcTest(DepartmentController.class)
class DepartmentControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DepartmentService departmentService;

    @MockBean
    private DepartmentDtoService departmentDtoService;

    @Captor
    private ArgumentCaptor<Department> captor;

    @Test
    void showAllDepartmentsTest() throws Exception {
        List<DepartmentDto> departments = Arrays.asList(
                new DepartmentDto("IT", 100, 2),
                new DepartmentDto("MANAGEMENT", 200, 1),
                new DepartmentDto("SECURITY", 300, 0)
        );
        when(departmentDtoService.findAllDepartments()).thenReturn(departments);
            mockMvc.perform(
                        MockMvcRequestBuilders.get("/departments")
                ).andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/html;charset=UTF-8"))
                .andExpect(view().name("departments"))
                .andExpect(model().attribute("departments", departments)
        );
    }

    @Test
    void showDepartmentCreatePageTest() throws Exception {
        mockMvc.perform(
                        MockMvcRequestBuilders.get("/department")
                ).andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/html;charset=UTF-8"))
                .andExpect(view().name("department"))
                .andExpect(model().attribute("isNew", is(true)))
                .andExpect(model().attribute("department", isA(Department.class)));
        verifyNoMoreInteractions(departmentDtoService, departmentService);
    }

    @Test
    void createDepartmentTest() throws Exception {
        mockMvc.perform(
                        MockMvcRequestBuilders.post("/department")
                                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                                .param("name", "New Department")
                ).andExpect(status().isFound())
                .andExpect(view().name("redirect:/departments"))
                .andExpect(redirectedUrl("/departments"));

        verify(departmentService).createDepartment(captor.capture());
        Department d = captor.getValue();
        assertEquals("New Department", d.getName());
    }

    @Test
    public void showDepartmentUpdatePageTest() throws Exception {
        Department d = new Department("IT");
        d.setId(1L);
        when(departmentService.findDepartmentById(any())).thenReturn(Optional.of(d));
        mockMvc.perform(
                        MockMvcRequestBuilders.get("/department/" + d.getId())
                ).andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/html;charset=UTF-8"))
                .andExpect(view().name("department"))
                .andExpect(model().attribute("isNew", is(false)))
                .andExpect(model().attribute("department", hasProperty("id", is(d.getId()))))
                .andExpect(model().attribute("department", hasProperty("name", is(d.getName()))));
    }

    @Test
    void updateDepartmentTest() throws Exception {
        mockMvc.perform(
                        MockMvcRequestBuilders.post("/department/1")
                                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                                .param("id", "1")
                                .param("name", "Updated Department")
                ).andExpect(status().isFound())
                .andExpect(view().name("redirect:/departments"))
                .andExpect(redirectedUrl("/departments"));

        verify(departmentService).updateDepartment(captor.capture());
        Department d = captor.getValue();
        assertEquals("Updated Department", d.getName());
    }

    @Test
    void deleteDepartmentByIdTest() throws Exception {
        mockMvc.perform(
                        MockMvcRequestBuilders.get("/department/3/delete")
                ).andExpect(status().isFound())
                .andExpect(view().name("redirect:/departments"))
                .andExpect(redirectedUrl("/departments"));

        verify(departmentService).deleteDepartmentById(3L);
    }
}