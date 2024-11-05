package com.ntg.backend.service;

import com.ntg.backend.dto.VolunteerDto;
import com.ntg.backend.entity.User;
import com.ntg.backend.entity.Volunteer;

import java.util.List;

public interface UserService {
    User createUser(User user);
    List<User> getAllUsers();
}
