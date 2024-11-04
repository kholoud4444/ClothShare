package com.ntg.backend.service;

import com.ntg.backend.entity.User;

import java.util.List;

public interface UserService {
    User saveUser(User user);
    List<User> getAllUsers();
}
