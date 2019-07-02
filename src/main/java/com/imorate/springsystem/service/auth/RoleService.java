package com.imorate.springsystem.service.auth;

import com.imorate.springsystem.model.auth.Role;

public interface RoleService {
    Role findByRole(String role);
}
