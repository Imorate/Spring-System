package com.imorate.springsystem.service.auth;

import com.imorate.springsystem.model.auth.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
public class SecurityServiceImpl implements SecurityService {
    private final UserService userService;
    private final HttpServletRequest request;

    public SecurityServiceImpl(UserService userService, HttpServletRequest request) {
        this.userService = userService;
        this.request = request;
    }

    @Override
    public String findLoggedInUsername() {
        Object userDetails = SecurityContextHolder.getContext().getAuthentication().getDetails();
        if (userDetails instanceof UserDetails) {
            return ((UserDetails) userDetails).getUsername();
        }
        return null;
    }

    @Override
    public boolean hasRole(String role) {
        return request.isUserInRole(role);
    }

    @Override
    public User findLoggedInUser() {
        Object userDetails = SecurityContextHolder.getContext().getAuthentication().getDetails();
        if (!(userDetails instanceof AnonymousAuthenticationToken)) {
            return userService.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        }
        return null;
    }
}
