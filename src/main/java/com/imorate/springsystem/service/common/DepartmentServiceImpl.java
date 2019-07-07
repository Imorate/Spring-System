package com.imorate.springsystem.service.common;

import com.imorate.springsystem.model.common.Department;
import com.imorate.springsystem.repository.common.DepartmentRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;

    public DepartmentServiceImpl(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @Override
    @Transactional
    public List<Department> findAll() {
        return departmentRepository.findAll();
    }

    @Transactional
    @Override
    public Optional<Department> findById(Integer id) {
        return departmentRepository.findById(id);
    }

}
