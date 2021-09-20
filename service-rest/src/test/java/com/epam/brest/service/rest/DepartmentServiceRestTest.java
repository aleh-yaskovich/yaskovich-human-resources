package com.epam.brest.service.rest;

import com.epam.brest.entity.Department;
import com.epam.brest.entity.DepartmentDto;
import com.epam.brest.service.DepartmentDtoService;
import com.epam.brest.service.DepartmentService;
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
class DepartmentServiceRestTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(DepartmentServiceRestTest.class);

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    DepartmentService departmentService;

    @Autowired
    DepartmentDtoService departmentDtoService;

    private MockRestServiceServer mockServer;

    private ObjectMapper mapper = new ObjectMapper();

    @BeforeEach
    public void before() {
        mockServer = MockRestServiceServer.createServer(restTemplate);
    }

    @Test
    void findAllDepartmentsTest() throws Exception {
        LOGGER.debug("findAllDepartmentsTest()");
        // given
        List<DepartmentDto> departmentList = Arrays.asList(
                new DepartmentDto("IT", 100),
                new DepartmentDto("MANAGEMENT", 200),
                new DepartmentDto("SECURITY", 300)
        );

        mockServer.expect(ExpectedCount.once(), requestTo("http://localhost:8080/departments"))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withStatus(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(mapper.writeValueAsString(departmentList))
                );

        // when
        List<DepartmentDto> departmentDtos = departmentDtoService.findAllDepartments();

        // then
        mockServer.verify();
        assertNotNull(departmentDtos);
        assertTrue(departmentDtos.size() == 3);
    }

    @Test
    void findDepartmentByIdTest() throws Exception {
        LOGGER.debug("findDepartmentByIdTest()");
        // given
        Long id = 1L;
        Department department = new Department("Test");
        department.setId(id);

        mockServer.expect(ExpectedCount.once(), requestTo("http://localhost:8080/departments" + "/" + id))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withStatus(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(mapper.writeValueAsString(department))
                );

        // when
        Optional<Department> optionalDepartment = departmentService.findDepartmentById(id);

        // then
        mockServer.verify();
        assertTrue(optionalDepartment.isPresent());
        assertEquals(optionalDepartment.get().getId(), id);
        assertEquals(optionalDepartment.get().getName(), department.getName());
    }

    @Test
    void createDepartmentTest() throws Exception {
        LOGGER.debug("createDepartmentTest()");
        // given
        Long id = 1L;
        Department department = new Department("Test");
        department.setId(id);

        mockServer.expect(ExpectedCount.once(), requestTo(new URI("http://localhost:8080/departments")))
                .andExpect(method(HttpMethod.POST))
                .andRespond(withStatus(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(mapper.writeValueAsString(department))
                );
        // when
        Department result = departmentService.createDepartment(department);

        // then
        mockServer.verify();
        assertEquals(department, result);
    }

    @Test
    void updateDepartmentTest() throws Exception {
        LOGGER.debug("updateDepartmentTest()");
        // given
        Long id = 1L;
        Department department = new Department("Test");
        department.setId(id);

        mockServer.expect(ExpectedCount.once(), requestTo(new URI("http://localhost:8080/departments")))
                .andExpect(method(HttpMethod.PUT))
                .andRespond(withStatus(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(mapper.writeValueAsString(department))
                );
        // when
        Department result = departmentService.updateDepartment(department);

        // then
        mockServer.verify();
        assertEquals(department, result);
    }

    @Test
    void deleteDepartmentByIdTest() throws Exception {
        LOGGER.debug("deleteDepartmentByIdTest()");
        // given
        Long id = 1L;
        mockServer.expect(ExpectedCount.once(), requestTo(new URI("http://localhost:8080/departments" + "/" + id)))
                .andExpect(method(HttpMethod.DELETE))
                .andRespond(withStatus(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                );
        // when
        departmentService.deleteDepartmentById(id);

        // then
        mockServer.verify();
    }
}