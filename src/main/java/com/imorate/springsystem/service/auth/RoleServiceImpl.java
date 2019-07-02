package com.imorate.springsystem.service.auth;

import com.imorate.springsystem.model.auth.Role;
import com.imorate.springsystem.repository.auth.RoleRepository;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role findByRole(String role) {
        return roleRepository.findByRole(role);
    }
}
