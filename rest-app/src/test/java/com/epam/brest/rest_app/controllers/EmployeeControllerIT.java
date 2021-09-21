package com.epam.brest.rest_app.controllers;

import com.epam.brest.entity.Employee;
import com.epam.brest.rest_app.exception.CustomExceptionHandler;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@Transactional
class EmployeeControllerIT {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeControllerIT.class);

    @Autowired
    private EmployeeController employeeController;

    @Autowired
    private CustomExceptionHandler customExceptionHandler;

    @Autowired
    protected ObjectMapper objectMapper;

    protected MockMvc mockMvc;

    protected MockEmployeeService employeeService = new MockEmployeeService();

    @BeforeEach
    void setUp() {
        this.mockMvc = standaloneSetup(employeeController)
                .setMessageConverters(new MappingJackson2HttpMessageConverter())
                .setControllerAdvice(customExceptionHandler)
                .alwaysDo(MockMvcResultHandlers.print())
                .build();
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findAllTest() throws Exception {
        Employee employee = new Employee("FirstName1", "LastName1", "mail33@mail.com", 500, 1L);
        employeeService.createEmployee(employee);
        List<Employee> employees = employeeService.findAllEmployees();
        assertNotNull(employees);
        assertTrue(employees.size() > 0);
    }

    @Test
    void findByIdTest() throws Exception {
        Employee employee = new Employee("FirstName1", "LastName1", "mail99@mail.com", 500, 1L);
        Employee employeeReturned = employeeService.createEmployee(employee);
        assertNotNull(employeeReturned);

        Optional<Employee> optionalEmployee = employeeService.findEmployeeById(employeeReturned.getId());
        assertTrue(optionalEmployee.isPresent());
        assertEquals(employeeReturned, optionalEmployee.get());
    }

    @Test
    void createEmployeeTest() throws Exception {
        List<Employee> employees = employeeService.findAllEmployees();
        int sizeBefore = employees.size();
        Employee employee = new Employee("FirstName1", "LastName1", "mail1@mail.com", 500, 1L);

        Employee employeeReturned = employeeService.createEmployee(employee);
        employees = employeeService.findAllEmployees();
        int sizeAfter = employees.size();

        assertTrue(sizeBefore == (sizeAfter - 1));
        assertEquals(employee.getFirstName(), employeeReturned.getFirstName());
        assertEquals(employee.getLastName(), employeeReturned.getLastName());
    }

    @Test
    void updateEmployeeTest() throws Exception {
        Employee employee = new Employee("FirstName1", "LastName1", "mail100@mail.com", 500, 1L);

        Employee employeeReturned = employeeService.createEmployee(employee);
        assertEquals(employee.getEmail(), employeeReturned.getEmail());
        List<Employee> employees = employeeService.findAllEmployees();
        int sizeBefore = employees.size();

        employeeReturned.setFirstName("NEW NAME");
        Employee employeeUpdated = employeeService.updateEmployee(employeeReturned);
        employees = employeeService.findAllEmployees();
        int sizeAfter = employees.size();

        assertTrue(sizeBefore == sizeAfter);
        assertEquals(employeeUpdated, employeeReturned);
    }

    @Test
    void deleteEmployeeTest() throws Exception {
        Employee employee = new Employee("FirstName1", "LastName1", "mail101@mail.com", 500, 1L);

        Employee employeeReturned = employeeService.createEmployee(employee);
        assertEquals(employee.getEmail(), employeeReturned.getEmail());
        List<Employee> employees = employeeService.findAllEmployees();
        int sizeBefore = employees.size();

        employeeService.deleteEmployeeById(employeeReturned.getId());
        employees = employeeService.findAllEmployees();
        int sizeAfter = employees.size();
        assertTrue((sizeBefore - 1) == sizeAfter);
    }

    //////////////////////////////////////////

    private class MockEmployeeService {

        public List<Employee> findAllEmployees() throws Exception {
            LOGGER.debug("findAllEmployees()");
            MockHttpServletResponse response = mockMvc.perform(get("/employees")
                            .accept(MediaType.APPLICATION_JSON)
                    ).andExpect(status().isOk())
                    .andReturn().getResponse();
            assertNotNull(response);

            return objectMapper.readValue(response.getContentAsString(), new TypeReference<List<Employee>>() {
            });
        }

        public Optional<Employee> findEmployeeById(Long id) throws Exception {
            LOGGER.debug("findEmployeeById({})", id);
            MockHttpServletResponse response = mockMvc.perform(get("/employees/" + id)
                            .accept(MediaType.APPLICATION_JSON)
                    ).andExpect(status().isOk())
                    .andReturn().getResponse();
            return Optional.of(objectMapper.readValue(response.getContentAsString(), Employee.class));
        }

        public Employee createEmployee(Employee employee) throws Exception {
            LOGGER.debug("createEmployee({})", employee);
            String json = objectMapper.writeValueAsString(employee);
            MockHttpServletResponse response =
                    mockMvc.perform(post("/employees")
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .content(json)
                                    .accept(MediaType.APPLICATION_JSON)
                            ).andExpect(status().isCreated())
                            .andReturn().getResponse();
            return objectMapper.readValue(response.getContentAsString(), Employee.class);
        }

        public Employee updateEmployee(Employee employee) throws Exception {
            LOGGER.debug("updateEmployee({})", employee);
            MockHttpServletResponse response =
                    mockMvc.perform(put("/employees")
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .content(objectMapper.writeValueAsString(employee))
                                    .accept(MediaType.APPLICATION_JSON)
                            ).andExpect(status().is(201))
                            .andReturn().getResponse();
            return objectMapper.readValue(response.getContentAsString(), Employee.class);
        }

        public void deleteEmployeeById(Long id) throws Exception {
            LOGGER.debug("deleteEmployeeById({})", id);
            MockHttpServletResponse response = mockMvc.perform(
                            MockMvcRequestBuilders.delete(new StringBuilder("/employees").append("/")
                                            .append(id).toString())
                                    .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().is(201))
                    .andReturn().getResponse();
        }
    }
}