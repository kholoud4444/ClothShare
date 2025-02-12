package com.ntg.backend.Mapper;

import com.ntg.backend.dto.ResponsePagination.PageDto;
import com.ntg.backend.dto.requestDto.RegisterDto;
import com.ntg.backend.dto.responseDto.UserResponseDetails;
import com.ntg.backend.entity.Needy;
import com.ntg.backend.entity.Volunteer;

import com.ntg.backend.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMapper {

    public User toUser(RegisterDto registrationDto) {
        User user;
        if ("volunteer".equalsIgnoreCase(registrationDto.getRole())) {
            user = new Volunteer();
        } else if ("needy".equalsIgnoreCase(registrationDto.getRole())) {
            user = new Needy();
        } else {
            throw new IllegalArgumentException("Invalid role: " + registrationDto.getRole());
        }

        user.setFirstName(registrationDto.getFirstName());
        user.setLastName(registrationDto.getLastName());
        user.setPassword(registrationDto.getPassword()); // Password encoding happens in the service
        user.setEmail(registrationDto.getEmail());
        user.setPhone(registrationDto.getPhone());
        user.setNationalId(registrationDto.getNationalId());
        user.setGender(registrationDto.getGender());
        user.setBirthDate(registrationDto.getBirthDate());
        user.setLocation(registrationDto.getLocation());
        user.setRole(registrationDto.getRole());

        return user;
    }

//    public UserResponseDetails mapToUserDto(User user) {
//        if (user == null) {
//            return null;
//        }
//
//        UserResponseDetails userDto = new UserResponseDetails();
//        userDto.setUserId(user.getUserId());
//        userDto.setFirstName(user.getFirstName());
//        userDto.setLastName(user.getLastName());
//        userDto.setRole(user.getRole());
//        userDto.setEmail(user.getEmail());
//        userDto.setPhone(user.getPhone());
//        userDto.setNationalId(user.getNationalId());
//        userDto.setGender(user.getGender());
//        userDto.setBirthDate(user.getBirthDate());
//        userDto.setLocation(user.getLocation());
//
//        return userDto;
//    }

    public void mapToUserDto(User user, UserResponseDetails userResponseDetails) {
        userResponseDetails.setUserId(user.getUserId());
        userResponseDetails.setFirstName(user.getFirstName());
        userResponseDetails.setLastName(user.getLastName());
        userResponseDetails.setRole(user.getRole());
        userResponseDetails.setEmail(user.getEmail());
        userResponseDetails.setPhone(user.getPhone());
        userResponseDetails.setNationalId(user.getNationalId());
        userResponseDetails.setGender(user.getGender());
        userResponseDetails.setBirthDate(user.getBirthDate());
        userResponseDetails.setLocation(user.getLocation());
    }

    public void updateUserDtoToEntity(UserResponseDetails userResponseDetails, User user) {

        if (userResponseDetails.getLocation() != null) {
            user.setLocation(userResponseDetails.getLocation());
        }
        if (userResponseDetails.getGender() != null) {
            user.setGender(userResponseDetails.getGender());
        }
        if (userResponseDetails.getPhone() != null) {
            user.setPhone(userResponseDetails.getPhone());
        }
        if (userResponseDetails.getFirstName() != null) {
            user.setFirstName(userResponseDetails.getFirstName());
        }
        if (userResponseDetails.getLastName() != null) {
            user.setLastName(userResponseDetails.getLastName());
        }
        if (userResponseDetails.getBirthDate() != null) {
            user.setBirthDate(userResponseDetails.getBirthDate());
        }
    }
    public PageDto<UserResponseDetails> userPageToDto(Page<User> userDetailsPage) {
        List<UserResponseDetails> userResponseDetails = userDetailsPage.getContent().stream()
                .map(user -> {
                    UserResponseDetails userResponseDetails1 = new UserResponseDetails();
                    this.mapToUserDto(user, userResponseDetails1); // Map each user to UserResponseDetails
                    return userResponseDetails1;
                })
                .collect(Collectors.toList());

        // Return a new PageDto with the mapped content and pagination details
        return new PageDto<>(userResponseDetails,userDetailsPage.getTotalElements()
                , userDetailsPage.getTotalPages(),userDetailsPage.getNumber(),
                userDetailsPage.getSize(),userDetailsPage.isLast() );
    };
}
