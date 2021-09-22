package com.epam.brest.service.rest;

import com.epam.brest.entity.Employee;
import com.epam.brest.entity.EmployeeDto;
import com.epam.brest.service.EmployeeDtoService;
import com.epam.brest.service.EmployeeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.client.ExpectedCount;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withStatus;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = TestConfig.class)
class EmployeeServiceRestTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeServiceRestTest.class);

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    EmployeeService employeeService;

    @Autowired
    EmployeeDtoService employeeDtoService;

    private String url = "http://localhost:8080/employees";

    private MockRestServiceServer mockServer;

    private ObjectMapper mapper = new ObjectMapper();

    @BeforeEach
    public void before() {
        mockServer = MockRestServiceServer.createServer(restTemplate);
    }

    @Test
    void findAllEmployeesTest() throws Exception {
        LOGGER.debug("findAllEmployeesTest()");
        // given
        List<EmployeeDto> employeeDtoList = Arrays.asList(new EmployeeDto(), new EmployeeDto());

        mockServer.expect(ExpectedCount.once(), requestTo(url + "-dto"))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withStatus(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(mapper.writeValueAsString(employeeDtoList))
                );

        // when
        List<EmployeeDto> employees = employeeDtoService.findAllEmployees();

        // then
        mockServer.verify();
        assertNotNull(employees);
        assertTrue(employees.size() == 2);
    }

    @Test
    void findAllTest() throws Exception {
        LOGGER.debug("findAllTest()");
        // given
        List<Employee> employeeList = Arrays.asList(new Employee(), new Employee());

        mockServer.expect(ExpectedCount.once(), requestTo(url))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withStatus(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(mapper.writeValueAsString(employeeList))
                );

        // when
        List<Employee> employees = employeeService.findAll();

        // then
        mockServer.verify();
        assertNotNull(employees);
        assertTrue(employees.size() == 2);
    }

    @Test
    void findEmployeeByIdTest() throws Exception {
        LOGGER.debug("findEmployeeByIdTest()");
        // given
        Long id = 1L;
        Employee employee = new Employee("FirstName1", "LastName1", "mail1@mail.com", 500, 1L);
        employee.setId(id);

        mockServer.expect(ExpectedCount.once(), requestTo(url + "/" + id))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withStatus(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(mapper.writeValueAsString(employee))
                );

        // when
        Optional<Employee> optionalEmployee = employeeService.findEmployeeById(id);

        // then
        mockServer.verify();
        assertTrue(optionalEmployee.isPresent());
        assertEquals(optionalEmployee.get().getId(), id);
        assertEquals(optionalEmployee.get().getEmail(), employee.getEmail());
    }

    @Test
    void createEmployeeTest() throws Exception {
        LOGGER.debug("createEmployeeTest()");
        // given
        Long id = 1L;
        Employee employee = new Employee("FirstName1", "LastName1", "mail1@mail.com", 500, 1L);
        employee.setId(id);

        mockServer.expect(ExpectedCount.once(), requestTo(new URI(url)))
                .andExpect(method(HttpMethod.POST))
                .andRespond(withStatus(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(mapper.writeValueAsString(employee))
                );
        // when
        Employee result = employeeService.createEmployee(employee);

        // then
        mockServer.verify();
        assertEquals(employee, result);
    }

    @Test
    void updateEmployeeTest() throws Exception {
        LOGGER.debug("updateEmployeeTest()");
        Long id = 1L;
        Employee employee = new Employee("FirstName1", "LastName1", "mail1@mail.com", 500, 1L);
        employee.setId(id);

        mockServer.expect(ExpectedCount.once(), requestTo(new URI(url)))
                .andExpect(method(HttpMethod.PUT))
                .andRespond(withStatus(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(mapper.writeValueAsString(employee))
                );
        // when
        Employee result = employeeService.updateEmployee(employee);

        // then
        mockServer.verify();
        assertEquals(employee, result);
    }

    @Test
    void deleteEmployeeByIdTest() throws Exception {
        LOGGER.debug("deleteEmployeeByIdTest()");
        // given
        Long id = 1L;
        mockServer.expect(ExpectedCount.once(), requestTo(new URI(url + "/" + id)))
                .andExpect(method(HttpMethod.DELETE))
                .andRespond(withStatus(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                );
        // when
        employeeService.deleteEmployeeById(id);

        // then
        mockServer.verify();
    }
}