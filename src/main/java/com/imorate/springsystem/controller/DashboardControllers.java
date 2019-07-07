package com.imorate.springsystem.controller;

import com.imorate.springsystem.configuration.validator.StudentValidator;
import com.imorate.springsystem.configuration.validator.UserProfileValidator;
import com.imorate.springsystem.configuration.validator.UserValidator;
import com.imorate.springsystem.model.announcement.Announcement;
import com.imorate.springsystem.model.announcement.MasterAnnouncement;
import com.imorate.springsystem.model.auth.User;
import com.imorate.springsystem.service.announcement.MasterAnnouncementService;
import com.imorate.springsystem.service.auth.SecurityService;
import com.imorate.springsystem.service.auth.UserService;
import com.imorate.springsystem.service.common.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.beans.PropertyEditorSupport;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class DashboardControllers {

    private final SecurityService securityService;
    private final DepartmentService departmentService;
    private final UserValidator userValidator;
    private final UserProfileValidator userProfileValidator;
    private final StudentValidator studentValidator;
    private final UserService userService;
    private final MasterAnnouncementService masterAnnouncementService;

    public DashboardControllers(SecurityService securityService, DepartmentService departmentService, UserProfileValidator userProfileValidator, UserValidator userValidator, StudentValidator studentValidator, UserService userService, MasterAnnouncementService masterAnnouncementService) {
        this.securityService = securityService;
        this.departmentService = departmentService;
        this.userProfileValidator = userProfileValidator;
        this.userValidator = userValidator;
        this.studentValidator = studentValidator;
        this.userService = userService;
        this.masterAnnouncementService = masterAnnouncementService;
    }

    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");

        dataBinder.registerCustomEditor(Date.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String value) {
                try {
                    setValue(new SimpleDateFormat("yyyy-MM-dd").parse(value));
                } catch (ParseException e) {
                    setValue(null);
                }
            }
        });

    }

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        model.addAttribute("userProfile", securityService.findLoggedInUser().getUserProfile());
        return "dashboard/dashboard";
    }

    @GetMapping("/dashboard/user-profile")
    public String userProfile(Model model, RedirectAttributes attributes) {
        User user = securityService.findLoggedInUser();
        model.addAttribute("userProfile", user.getUserProfile());
        model.addAttribute("departmentList", departmentService.findAll());
        if (securityService.hasRole("ROLE_STUDENT")) {
            model.addAttribute("userDepartment", departmentService.findById(user.getStudent().getDepartment().getId()));
        } else {
            model.addAttribute("userDepartment", departmentService.findById(user.getMaster().getDepartment().getId()));
        }
        model.addAttribute("formUser", user);
        /*model.addAttribute("updateDone", attributes.getFlashAttributes().size());
        System.out.println(attributes.getFlashAttributes().size());*/
        return "dashboard/user-profile";
    }

    @PostMapping("/dashboard/user-profile/edit-profile")
    public String editProfile(@ModelAttribute("formUser") User formUser, RedirectAttributes attributes) {
        /*attributes.addFlashAttribute("updateDone", true);*/
        System.out.println(formUser);
        userService.update(formUser);
        return "redirect:/dashboard/user-profile";
    }

    @GetMapping("/dashboard/announcement")
    public String userProfile(Model model) {
        User user = securityService.findLoggedInUser();
        model.addAttribute("user", user);
        model.addAttribute("masterAnnouncement", new MasterAnnouncement());
        return "dashboard/announcement";
    }

    @PostMapping("/dashboard/announcement/add-master-announcement")
    public String addMasterAnnouncement(@ModelAttribute("masterAnnouncement") MasterAnnouncement masterAnnouncement) {
        masterAnnouncementService.saveMasterAnnouncement(masterAnnouncement);
        return "redirect:/dashboard/announcement";
    }
}
