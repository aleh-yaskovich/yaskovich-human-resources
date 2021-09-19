package com.epam.brest.web_app.controllers;

import com.epam.brest.entity.Employee;
import com.epam.brest.service.DepartmentDtoService;
import com.epam.brest.service.EmployeeService;
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

import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.isA;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(EmployeeController.class)
class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmployeeService employeeService;

    @MockBean
    private DepartmentDtoService departmentDtoService;

    @Captor
    private ArgumentCaptor<Employee> captor;

    @Test
    void showAllEmployeesTest() throws Exception {
        List<Employee> employees = Arrays.asList(
                new Employee("FirstName1", "LastName1", "mail1@mail.com", 500, 1L),
                new Employee("FirstName2", "LastName2", "mail2@mail.com", 600, 1L),
                new Employee("FirstName3", "LastName3", "mail3@mail.com", 1000, 2L)
        );
        when(employeeService.findAllEmployees()).thenReturn(employees);
        mockMvc.perform(
                        MockMvcRequestBuilders.get("/employees")
                ).andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/html;charset=UTF-8"))
                .andExpect(view().name("employees"))
                .andExpect(model().attribute("employees", employees)
        );
    }

    @Test
    void showEmployeeCreatePageTest() throws Exception {
        mockMvc.perform(
                        MockMvcRequestBuilders.get("/employee")
                ).andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/html;charset=UTF-8"))
                .andExpect(view().name("employee"))
                .andExpect(model().attribute("isNew", is(true)))
                .andExpect(model().attribute("employee", isA(Employee.class)));
        verifyNoMoreInteractions(employeeService);
    }

    @Test
    void createEmployeeTest() throws Exception {
        mockMvc.perform(
                        MockMvcRequestBuilders.post("/employee")
                                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                                .param("firstName", "NewFirstName")
                                .param("lastName", "NewLastName")
                                .param("email", "new@mail.com")
                ).andExpect(status().isFound())
                .andExpect(view().name("redirect:/employees"))
                .andExpect(redirectedUrl("/employees"));

        verify(employeeService).createEmployee(captor.capture());
        Employee e = captor.getValue();
        assertEquals("NewFirstName", e.getFirstName());
        assertEquals("NewLastName", e.getLastName());
        assertEquals("new@mail.com", e.getEmail());
    }

    @Test
    public void showEmployeeUpdatePageTest() throws Exception {
        Employee e = new Employee();
        e.setId(1L);
        e.setEmail("new@mail.com");
        when(employeeService.findEmployeeById(any())).thenReturn(Optional.of(e));
        mockMvc.perform(
                        MockMvcRequestBuilders.get("/employee/" + e.getId())
                ).andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/html;charset=UTF-8"))
                .andExpect(view().name("employee"))
                .andExpect(model().attribute("isNew", is(false)))
                .andExpect(model().attribute("employee", hasProperty("id", is(e.getId()))))
                .andExpect(model().attribute("employee", hasProperty("email", is(e.getEmail()))));
    }

    @Test
    void updateDepartmentTest() throws Exception {
        mockMvc.perform(
                        MockMvcRequestBuilders.post("/employee/1")
                                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                                .param("id", "1")
                                .param("email", "new@mail.com")
                ).andExpect(status().isFound())
                .andExpect(view().name("redirect:/employees"))
                .andExpect(redirectedUrl("/employees"));

        verify(employeeService).updateEmployee(captor.capture());
        Employee e = captor.getValue();
        assertEquals("new@mail.com", e.getEmail());
    }

    @Test
    void deleteDepartmentByIdTest() throws Exception {
        mockMvc.perform(
                        MockMvcRequestBuilders.get("/employee/3/delete")
                ).andExpect(status().isFound())
                .andExpect(view().name("redirect:/employees"))
                .andExpect(redirectedUrl("/employees"));

        verify(employeeService).deleteEmployeeById(3L);
    }
}