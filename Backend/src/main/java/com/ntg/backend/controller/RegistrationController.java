package com.ntg.backend.controller;

import com.ntg.backend.dto.requestDto.RegistrationDto;
import com.ntg.backend.entity.User;
import com.ntg.backend.service.imp.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/registration")
public class RegistrationController {

    @Autowired
    private UserServiceImp userService;

    @PostMapping
    public ResponseEntity<User> registerUser(@RequestBody RegistrationDto registrationDto) {
        // Call the service to create the user


        // Optional: Retrieve the created user from the database (if needed)
        User createdUser = userService.createUser(registrationDto);

        // Return a response with the created user and an HTTP status
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }
}
