package com.epam.brest.rest_app.controllers;

import com.epam.brest.entity.Department;
import com.epam.brest.rest_app.exception.CustomExceptionHandler;
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

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@Transactional
public class DepartmentControllerTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(DepartmentControllerTest.class);

    @Autowired
    private DepartmentController departmentController;

    @Autowired
    private CustomExceptionHandler customExceptionHandler;

    @Autowired
    protected ObjectMapper objectMapper;

    protected MockMvc mockMvc;

    protected MockDepartmentService departmentService = new MockDepartmentService();

    @BeforeEach
    void setUp() {
        this.mockMvc = standaloneSetup(departmentController)
                .setMessageConverters(new MappingJackson2HttpMessageConverter())
                .setControllerAdvice(customExceptionHandler)
                .alwaysDo(MockMvcResultHandlers.print())
                .build();
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findByIdTest() throws Exception {
        LOGGER.debug("findByIdTest()");
        Department department = new Department("Department");
        Department result = departmentService.createDepartment(department);
        Optional<Department> optional = departmentService.findDepartmentById(1L);
        assertNotNull(optional);
    }

    @Test
    void createDepartmentTest() throws Exception {
        LOGGER.debug("createDepartmentTest()");
        Department department = new Department("New Department");
        Department result = departmentService.createDepartment(department);
        assertNotNull(result);
        assertEquals(result.getName(), department.getName());
        departmentService.deleteDepartmentById(result.getId());
    }

    @Test
    void updateDepartmentTest() throws Exception {
        LOGGER.debug("updateDepartmentTest()");
        Department department = new Department("New Department");
        Department departmentReturned = departmentService.createDepartment(department);
        assertNotNull(departmentReturned);
        assertEquals(departmentReturned.getName(), department.getName());

        departmentReturned.setName("Updated");
        Department departmentUpdated = departmentService.updateDepartment(departmentReturned);

        Department departmentResult = departmentService.findDepartmentById(departmentReturned.getId()).get();
        assertEquals(departmentUpdated, departmentResult);
    }

    @Test
    void deleteDepartmentTest() throws Exception {
        LOGGER.debug("deleteDepartmentTest()");
        Department department = new Department("DEPARTMENT");
        Department departmentReturned = departmentService.createDepartment(department);
        Optional<Department> optionalDepartment = departmentService.findDepartmentById(departmentReturned.getId());
        assertTrue(optionalDepartment.isPresent());
        departmentService.deleteDepartmentById(departmentReturned.getId());
//        optionalDepartment = departmentService.findDepartmentById(departmentReturned.getId());
//        assertFalse(optionalDepartment.isPresent());
    }

    //////////////////////////////////////////

    private class MockDepartmentService {

        Optional<Department> findDepartmentById(Long id) throws Exception {
            LOGGER.debug("findDepartmentById({})", id);
            MockHttpServletResponse response = mockMvc.perform(get("/departments/" + id)
                            .accept(MediaType.APPLICATION_JSON)
                    ).andExpect(status().isOk())
                    .andReturn().getResponse();
            return Optional.of(objectMapper.readValue(response.getContentAsString(), Department.class));
        };

        Department createDepartment(Department department) throws Exception {
            LOGGER.debug("createDepartment({})", department);
            String json = objectMapper.writeValueAsString(department);
            MockHttpServletResponse response =
                    mockMvc.perform(post("/departments")
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .content(json)
                                    .accept(MediaType.APPLICATION_JSON)
                            ).andExpect(status().isCreated())
                            .andReturn().getResponse();
            return objectMapper.readValue(response.getContentAsString(), Department.class);
        };

        Department updateDepartment(Department department) throws Exception {
            LOGGER.debug("update({})", department);
            MockHttpServletResponse response =
                    mockMvc.perform(put("/departments")
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .content(objectMapper.writeValueAsString(department))
                                    .accept(MediaType.APPLICATION_JSON)
                            ).andExpect(status().is(201))
                            .andReturn().getResponse();
            return objectMapper.readValue(response.getContentAsString(), Department.class);
        };

        void deleteDepartmentById(Long id) throws Exception {
            LOGGER.debug("deleteDepartmentById({})", id);
            MockHttpServletResponse response = mockMvc.perform(
                            MockMvcRequestBuilders.delete(new StringBuilder("/departments").append("/")
                                            .append(id).toString())
                                    .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andReturn().getResponse();
        };
    }
}
