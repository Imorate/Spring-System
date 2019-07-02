package com.imorate.springsystem.service.auth;

import com.imorate.springsystem.model.auth.Role;
import com.imorate.springsystem.model.auth.User;
import com.imorate.springsystem.repository.auth.RoleRepository;
import com.imorate.springsystem.repository.auth.UserProfileRepository;
import com.imorate.springsystem.repository.auth.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashSet;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final RoleService roleService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserProfileRepository userProfileRepository;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, BCryptPasswordEncoder bCryptPasswordEncoder, RoleService roleService, UserProfileRepository userProfileRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.roleService = roleService;
        this.userProfileRepository = userProfileRepository;
    }

    @Override
    public void save(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        Role role = roleService.findByRole("ROLE_ADMIN");
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
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
