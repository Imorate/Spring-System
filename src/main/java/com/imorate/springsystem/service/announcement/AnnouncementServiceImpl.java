package com.imorate.springsystem.service.announcement;

import com.imorate.springsystem.model.announcement.Announcement;
import com.imorate.springsystem.repository.announcement.AnnouncementRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnnouncementServiceImpl implements AnnouncementService {

    private final AnnouncementRepository announcementRepository;

    public AnnouncementServiceImpl(AnnouncementRepository announcementRepository) {
        this.announcementRepository = announcementRepository;
    }

    @Override
    public List<Announcement> findAll() {
        return announcementRepository.findAll();
    }
}
