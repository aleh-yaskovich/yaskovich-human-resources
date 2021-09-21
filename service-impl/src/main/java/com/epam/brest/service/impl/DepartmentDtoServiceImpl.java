package com.epam.brest.service.impl;

import com.epam.brest.entity.DepartmentDto;
import com.epam.brest.repository.DepartmentDtoRepository;
import com.epam.brest.service.DepartmentDtoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class DepartmentDtoServiceImpl implements DepartmentDtoService {

    private final static Logger LOGGER = LoggerFactory.getLogger(DepartmentDtoServiceImpl.class);

    private final DepartmentDtoRepository repository;

    public DepartmentDtoServiceImpl(DepartmentDtoRepository repository) {
        this.repository = repository;
    }

    /**
     * Return all departments with average salary
     **/
    @Override
    public List<DepartmentDto> findAllDepartments() {
        LOGGER.debug("findAllDepartments()");
        return repository.findAll();
    }
}
