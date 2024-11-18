package com.ntg.backend.service;

import com.ntg.backend.dto.ResponsePagination.PageDto;
import com.ntg.backend.dto.requestDto.RegisterDto;
import com.ntg.backend.dto.responseDto.UserResponseDetails;
import com.ntg.backend.entity.User;

import java.util.List;

public interface UserService {
    User createUser(RegisterDto user);
    List<User> getAllUsers();
    UserResponseDetails getUserDetails(long id);
    void deleteUserById(long id);
    PageDto<UserResponseDetails> getAllUsersWithRole(String role, int pageNo, int pageSize);
    UserResponseDetails UpdateUser(long id, UserResponseDetails user);

}
