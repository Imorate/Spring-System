package com.imorate.springsystem.repository.common;

import com.imorate.springsystem.model.common.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
}
