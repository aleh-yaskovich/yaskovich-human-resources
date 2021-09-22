package com.epam.brest.service.impl;

import com.epam.brest.entity.EmployeeDto;
import com.epam.brest.repository.EmployeeDtoRepository;
import com.epam.brest.service.EmployeeDtoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class EmployeeDtoServiceImpl implements EmployeeDtoService {

    private final static Logger LOGGER = LoggerFactory.getLogger(EmployeeDtoServiceImpl.class);

    private final EmployeeDtoRepository repository;

    public EmployeeDtoServiceImpl(EmployeeDtoRepository repository) {
        this.repository = repository;
    }

    /**
     * Return all employees with department names
     **/
    @Override
    public List<EmployeeDto> findAllEmployees() {
        LOGGER.debug("findAllEmployees()");
        return repository.findAll();
    }
}
