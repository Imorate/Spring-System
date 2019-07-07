package com.imorate.springsystem.repository.announcement;

import com.imorate.springsystem.model.announcement.SystemAnnouncement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SystemAnnouncementRepository extends JpaRepository<SystemAnnouncement, Long> {
}
