package com.imorate.springsystem.repository.announcement;

import com.imorate.springsystem.model.announcement.MasterAnnouncement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MasterAnnouncementRepository extends JpaRepository<MasterAnnouncement,Long> {
}
