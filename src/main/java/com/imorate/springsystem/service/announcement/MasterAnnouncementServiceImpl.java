package com.imorate.springsystem.service.announcement;

import com.imorate.springsystem.model.announcement.MasterAnnouncement;
import com.imorate.springsystem.model.enumeration.AnnouncementType;
import com.imorate.springsystem.repository.announcement.MasterAnnouncementRepository;
import com.imorate.springsystem.service.auth.SecurityService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MasterAnnouncementServiceImpl implements MasterAnnouncementService {

    private final MasterAnnouncementRepository masterAnnouncementRepository;
    private final SecurityService securityService;

    public MasterAnnouncementServiceImpl(MasterAnnouncementRepository masterAnnouncementRepository, SecurityService securityService) {
        this.masterAnnouncementRepository = masterAnnouncementRepository;
        this.securityService = securityService;
    }

    @Override
    public void saveMasterAnnouncement(MasterAnnouncement masterAnnouncement) {
        masterAnnouncement.getAnnouncement().setUser(securityService.findLoggedInUser());
        masterAnnouncement.getAnnouncement().setAnnouncementType(AnnouncementType.MASTER_ANNOUNCEMENT);
        masterAnnouncementRepository.save(masterAnnouncement);
    }

    @Override
    public List<MasterAnnouncement> findAll() {
        return masterAnnouncementRepository.findAll();
    }
}
