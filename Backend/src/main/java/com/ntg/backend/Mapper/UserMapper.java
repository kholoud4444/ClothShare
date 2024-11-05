package com.ntg.backend.Mapper;

import com.ntg.backend.dto.RegistrationDto;
import com.ntg.backend.entity.Needy;
import com.ntg.backend.entity.Volunteer;

import com.ntg.backend.entity.User;
import com.ntg.backend.entity.Volunteer;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User toUser(RegistrationDto registrationDto) {
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
}

