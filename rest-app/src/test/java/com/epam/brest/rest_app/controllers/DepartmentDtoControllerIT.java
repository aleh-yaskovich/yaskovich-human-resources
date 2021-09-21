package com.epam.brest.rest_app.controllers;

import com.epam.brest.entity.DepartmentDto;
import org.junit.jupiter.api.Test;
import com.epam.brest.rest_app.exception.CustomExceptionHandler;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
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
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@Transactional
class DepartmentDtoControllerIT {

    private static final Logger LOGGER = LoggerFactory.getLogger(DepartmentDtoControllerIT.class);

    @Autowired
    private DepartmentDtoController departmentDtoController;

    @Autowired
    private CustomExceptionHandler customExceptionHandler;

    @Autowired
    protected ObjectMapper objectMapper;

    protected MockMvc mockMvc;

    protected MockDepartmentDtoService departmentDtoService = new MockDepartmentDtoService();

    @BeforeEach
    void setUp() {
        this.mockMvc = standaloneSetup(departmentDtoController)
                .setMessageConverters(new MappingJackson2HttpMessageConverter())
                .setControllerAdvice(customExceptionHandler)
                .alwaysDo(MockMvcResultHandlers.print())
                .build();
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void departmentsTest() throws Exception {
        LOGGER.debug("departmentsTest()");
        List<DepartmentDto> dtos = departmentDtoService.findAllDepartments();
        assertNotNull(dtos);
        assertTrue(dtos.isEmpty());
    }

    private class MockDepartmentDtoService {

        public List<DepartmentDto> findAllDepartments() throws Exception {
            LOGGER.debug("findAllDepartments()");
            MockHttpServletResponse response = mockMvc.perform(get("/departments")
                            .accept(MediaType.APPLICATION_JSON)
                    ).andExpect(status().isOk())
                    .andReturn().getResponse();
            assertNotNull(response);

            return objectMapper.readValue(response.getContentAsString(), new TypeReference<List<DepartmentDto>>() {
            });
        }
    }

}