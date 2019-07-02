package com.imorate.springsystem.service.common;

import com.imorate.springsystem.model.common.Department;
import com.imorate.springsystem.repository.common.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;

    public DepartmentServiceImpl(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @Override
    public List<Department> findAll() {
        return departmentRepository.findAll();
    }
}
