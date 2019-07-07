package com.imorate.springsystem.service.announcement;

import com.imorate.springsystem.model.announcement.SystemAnnouncement;

import java.util.List;

public interface SystemAnnouncementService {
    List<SystemAnnouncement> findAll();
}
