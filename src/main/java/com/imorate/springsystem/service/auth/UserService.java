package com.imorate.springsystem.service.auth;

import com.imorate.springsystem.model.auth.User;

public interface UserService {
    void save(User user);

    User findByUsername(String username);
}
