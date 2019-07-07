package com.imorate.springsystem.service.announcement;

import com.imorate.springsystem.model.announcement.Announcement;

import java.util.List;

public interface AnnouncementService {
    List<Announcement> findAll();
}
