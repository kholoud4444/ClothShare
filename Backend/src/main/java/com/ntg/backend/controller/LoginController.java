package com.ntg.backend.controller;

import com.ntg.backend.dto.requestDto.LoginDto;
import com.ntg.backend.entity.User;
import com.ntg.backend.jwt.JwtService;
import com.ntg.backend.service.imp.UserServiceImp;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LoginController {

    private final UserServiceImp userService;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public LoginController(UserServiceImp userService, AuthenticationManager authenticationManager, JwtService jwtService) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }


    @PostMapping("/login")
    public String login(@RequestBody LoginDto user) {

        Authentication authentication = authenticationManager.authenticate
                (new UsernamePasswordAuthenticationToken(user.getEmail(),user.getPassword()));

        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(user.getEmail()) ;
        }else {
            return "failure";
        }
    }

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

}
