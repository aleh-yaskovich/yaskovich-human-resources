package com.epam.brest.service.impl;

import com.epam.brest.entity.Employee;
import com.epam.brest.repository.EmployeeRepository;
import com.epam.brest.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

    private final static Logger LOGGER = LoggerFactory.getLogger(EmployeeServiceImpl.class);

    private final EmployeeRepository repository;

    public EmployeeServiceImpl(EmployeeRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Employee> findAllEmployees() {
        LOGGER.debug("findAllEmployees()");
        return repository.findAll();
    }

    @Override
    public Optional<Employee> findEmployeeById(Long id) {
        LOGGER.debug("findEmployeeById({})", id);
        return repository.findById(id);
    }

    @Override
    public Employee createEmployee(Employee employee) {
        LOGGER.debug("createEmployee({})", employee);
        if(checkEmployeeEmail(employee).size() == 0) {
            repository.save(employee);
            return employee;
        } else {
            throw new IllegalArgumentException("Employee with email \""+employee.getEmail()+"\" already exists");
        }
    }

    @Override
    public Employee updateEmployee(Employee employee) {
        LOGGER.debug("updateEmployee({})", employee);
        if(checkEmployeeEmail(employee).size() > 1) {
            throw new IllegalArgumentException("There is one more employee with email \""+employee.getEmail()+"\"");
        } else {
            repository.save(employee);
            return employee;
        }
    }

    @Override
    public void deleteEmployeeById(Long id) {
        LOGGER.debug("deleteEmployeeById({})", id);
        Optional<Employee> optionalEmployee = repository.findById(id);
        if(optionalEmployee.isPresent()) {
            repository.deleteById(id);
        } else {
            throw new IllegalArgumentException("There is no employee with ID "+id);
        }
    }

    private List<Employee> checkEmployeeEmail(Employee employee) {
        return repository.findByEmail(employee.getEmail());
    }
}
