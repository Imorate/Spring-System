package com.imorate.springsystem.service.auth;

import com.imorate.springsystem.model.auth.Role;
import com.imorate.springsystem.model.auth.User;
import com.imorate.springsystem.repository.auth.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashSet;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleService roleService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final SecurityService securityService;

    public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder, RoleService roleService, @Lazy SecurityService securityService) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.roleService = roleService;
        this.securityService = securityService;
    }

    @Override
    public void save(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        Role role = roleService.findByRole("ROLE_STUDENT");
        user.setRoles(new HashSet<>(Collections.singletonList(role)));
        user.setAccountNonExpired(true);
        user.setAccountNonLocked(true);
        user.setCredentialsNonExpired(true);
        user.setEnable(true);
        user.getUserProfile().setUser(user);
        user.getStudent().setUser(user);
        userRepository.save(user);
        log.info("User created: " + user);
    }

    @Override
    public void update(User user) {
        user.setId(securityService.findLoggedInUser().getId());
        user.setUsername(securityService.findLoggedInUser().getUsername());
        user.setPassword(securityService.findLoggedInUser().getPassword());
        user.setEnable(securityService.findLoggedInUser().isEnable());
        user.setAccountNonExpired(securityService.findLoggedInUser().isAccountNonExpired());
        user.setAccountNonLocked(securityService.findLoggedInUser().isAccountNonLocked());
        user.setCredentialsNonExpired(securityService.findLoggedInUser().isCredentialsNonExpired());
        user.setRoles(securityService.findLoggedInUser().getRoles());
        user.getUserProfile().setUser(user);
        user.getUserProfile().setId(securityService.findLoggedInUser().getId());
        //user.getStudent().setStudentID(securityService.findLoggedInUser().getStudent().getStudentID());
        userRepository.save(user);
        log.info("User updated: " + user);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public Long userCount() {
        return userRepository.count();
    }
}
