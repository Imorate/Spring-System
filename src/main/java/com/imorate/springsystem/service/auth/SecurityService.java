package com.imorate.springsystem.service.auth;

import com.imorate.springsystem.model.auth.User;

public interface SecurityService {
    String findLoggedInUsername();

    boolean hasRole(String role);

    User findLoggedInUser();
}
