package com.imorate.springsystem.service.auth;

import com.imorate.springsystem.model.auth.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class SecurityServiceImpl implements SecurityService {
    private static final Logger logger = LoggerFactory.getLogger(SecurityServiceImpl.class);
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final UserService userService;

    public SecurityServiceImpl(AuthenticationManager authenticationManager, @Qualifier("userDetailsServiceImpl") UserDetailsService userDetailsService, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.userService = userService;
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
    public User findLoggedInUser() {
        Object userDetails = SecurityContextHolder.getContext().getAuthentication().getDetails();
        if (!(userDetails instanceof AnonymousAuthenticationToken)) {
            return userService.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        }
        return null;
    }
}
