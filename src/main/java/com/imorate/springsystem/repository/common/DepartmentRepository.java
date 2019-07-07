package com.imorate.springsystem.repository.common;

import com.imorate.springsystem.model.common.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Integer> {
    Optional<Department> findById(Integer id);
}
