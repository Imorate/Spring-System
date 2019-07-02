package com.imorate.springsystem.repository.common;

import com.imorate.springsystem.model.common.Master;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MasterRepository extends JpaRepository<Master, Long> {
}
