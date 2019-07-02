package com.imorate.springsystem.controller;

import com.imorate.springsystem.service.auth.SecurityService;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
    private final SecurityService securityService;

    public IndexController(SecurityService securityService) {
        this.securityService = securityService;
    }

    @GetMapping("/")
    public String index(Model model) {
        if (!(SecurityContextHolder.getContext().getAuthentication() instanceof AnonymousAuthenticationToken)) {
            model.addAttribute("user", securityService.findLoggedInUser());
        }
        return "index";
    }
}
