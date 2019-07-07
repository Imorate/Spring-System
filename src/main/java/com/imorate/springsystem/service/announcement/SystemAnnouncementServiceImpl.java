package com.imorate.springsystem.service.announcement;

import com.imorate.springsystem.model.announcement.SystemAnnouncement;
import com.imorate.springsystem.repository.announcement.SystemAnnouncementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SystemAnnouncementServiceImpl implements SystemAnnouncementService {

    private final SystemAnnouncementRepository systemAnnouncementRepository;

    public SystemAnnouncementServiceImpl(SystemAnnouncementRepository systemAnnouncementRepository) {
        this.systemAnnouncementRepository = systemAnnouncementRepository;
    }

    @Override
    public List<SystemAnnouncement> findAll() {
        return systemAnnouncementRepository.findAll();
    }
}
