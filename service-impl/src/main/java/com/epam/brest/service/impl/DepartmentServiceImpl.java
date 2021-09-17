package com.epam.brest.service.impl;

import com.epam.brest.entity.Department;
import com.epam.brest.repository.DepartmentRepository;
import com.epam.brest.service.DepartmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class DepartmentServiceImpl implements DepartmentService {

    private final static Logger LOGGER = LoggerFactory.getLogger(DepartmentServiceImpl.class);

    private final DepartmentRepository repository;

    public DepartmentServiceImpl(DepartmentRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<Department> findDepartmentById(Long id) {
        LOGGER.debug("findDepartmentById({})", id);
        return repository.findById(id);
    }

    @Override
    public Department createDepartment(Department department) throws IllegalArgumentException {
        LOGGER.debug("createDepartment({})", department);
        if(checkDepartmentName(department).size() == 0) {
            repository.save(department);
            return department;
        } else {
            throw new IllegalArgumentException("Department with name \""+department.getName()+"\" already exists");
        }
    }

    @Override
    public Department updateDepartment(Department department) throws IllegalArgumentException {
        LOGGER.debug("updateDepartment({})", department);
        if(checkDepartmentName(department).size() > 1) {
            throw new IllegalArgumentException("There is one more department with name \""+department.getName()+"\"");
        } else {
            repository.save(department);
            return department;
        }
    }

    @Override
    public void deleteDepartmentById(Long id) throws IllegalArgumentException {
        LOGGER.debug("deleteDepartmentById({})", id);
        Optional<Department> optionalDepartment = repository.findById(id);
        if(optionalDepartment.isPresent()) {
            repository.deleteById(id);
        } else {
            throw new IllegalArgumentException("There is no department with ID "+id);
        }
    }

    private List<Department> checkDepartmentName(Department department) {
        return repository.findByName(department.getName());
    }
}
