package com.ntg.backend.service;

import com.ntg.backend.dto.requestDto.RegistrationDto;
import com.ntg.backend.dto.responseDto.UserResponseDetails;
import com.ntg.backend.entity.User;

import java.util.List;

public interface UserService {
    User createUser(RegistrationDto user);
    List<User> getAllUsers();
    UserResponseDetails getUserDetails(long id);
    void deleteUserById(long id);
    List<UserResponseDetails> getAllUsersWithRole(String role);
    UserResponseDetails UpdateUser(long id, UserResponseDetails user);

}
