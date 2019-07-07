package com.imorate.springsystem.service.announcement;

import com.imorate.springsystem.model.announcement.MasterAnnouncement;

import java.util.List;

public interface MasterAnnouncementService {
    void saveMasterAnnouncement(MasterAnnouncement masterAnnouncement);

    List<MasterAnnouncement> findAll();
}
