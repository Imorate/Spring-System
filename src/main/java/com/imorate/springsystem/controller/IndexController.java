package com.imorate.springsystem.controller;

import com.imorate.springsystem.service.announcement.MasterAnnouncementService;
import com.imorate.springsystem.service.announcement.SystemAnnouncementService;
import com.imorate.springsystem.service.auth.SecurityService;
import com.imorate.springsystem.service.auth.UserService;
import com.imorate.springsystem.service.common.DepartmentService;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    private final SecurityService securityService;
    private final MasterAnnouncementService masterAnnouncementService;
    private final DepartmentService departmentService;
    private final UserService userService;
    private final SystemAnnouncementService systemAnnouncementService;

    public IndexController(SecurityService securityService, MasterAnnouncementService masterAnnouncementService, DepartmentService departmentService, UserService userService, SystemAnnouncementService systemAnnouncementService) {
        this.securityService = securityService;
        this.masterAnnouncementService = masterAnnouncementService;
        this.departmentService = departmentService;
        this.userService = userService;
        this.systemAnnouncementService = systemAnnouncementService;
    }

    @GetMapping("/")
    public String index(Model model) {
        if (!(SecurityContextHolder.getContext().getAuthentication() instanceof AnonymousAuthenticationToken)) {
            model.addAttribute("user", securityService.findLoggedInUser().getUserProfile());
        }
        model.addAttribute("masterAnnouncements", masterAnnouncementService.findAll());
        model.addAttribute("systemAnnouncements", systemAnnouncementService.findAll());
        model.addAttribute("userCount", userService.userCount());
        return "index";
    }
}
