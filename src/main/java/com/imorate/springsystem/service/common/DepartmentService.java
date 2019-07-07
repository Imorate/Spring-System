package com.imorate.springsystem.service.common;

import com.imorate.springsystem.model.common.Department;

import java.util.List;
import java.util.Optional;

public interface DepartmentService {
    List<Department> findAll();
    Optional<Department> findById(Integer id);
}
