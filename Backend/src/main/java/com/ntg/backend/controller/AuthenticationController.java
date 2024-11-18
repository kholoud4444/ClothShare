package com.ntg.backend.controller;


import com.ntg.backend.dto.requestDto.AuthenticationRequestBody;
import com.ntg.backend.dto.requestDto.MessageDto;
import com.ntg.backend.dto.requestDto.RegisterDto;
import com.ntg.backend.dto.responseDto.AuthenticationResponseBody;
import com.ntg.backend.entity.User;
import com.ntg.backend.service.imp.AuthenticationService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/authentication")
public class AuthenticationController {
    private final AuthenticationService authenticationUserService;

    public AuthenticationController(AuthenticationService authenticationUserService) {
        this.authenticationUserService = authenticationUserService;
    }


    @PostMapping("/login")
    public AuthenticationResponseBody loginPage(@Valid @RequestBody AuthenticationRequestBody loginRequestBody) {
        return authenticationUserService.login(loginRequestBody);
    }

    @PostMapping("/register")
    public AuthenticationResponseBody registerPage(@Valid @RequestBody RegisterDto registerRequestBody) {
        return authenticationUserService.register(registerRequestBody);

    }

//    @GetMapping("/user")
//    public User getUser(@RequestAttribute("authenticatedUser") User user) {
//        return user;
//    }

    @PutMapping("/validate-email-verification-token")
    public  ResponseEntity<MessageDto<String>> verifyEmail(@RequestParam String token, @RequestAttribute("authenticatedUser") User user) {
        authenticationUserService.validateEmailVerificationToken(token, user.getEmail());
        return new ResponseEntity<>(new MessageDto<>("Email verified successfully", null), HttpStatus.OK);
    }

    @PostMapping("/send-email-verification-token")
    public ResponseEntity<MessageDto<String>>  sendEmailVerificationToken(@RequestAttribute("authenticatedUser") User user) {
        authenticationUserService.sendEmailVerificationToken(user.getEmail());
        return new ResponseEntity<>(new MessageDto<>("Email verification token sent successfully", null), HttpStatus.OK);
    }

    @PutMapping("/send-password-reset-token")
    public  ResponseEntity<MessageDto<String>> sendPasswordResetToken(@RequestParam String email) {
        authenticationUserService.sendPasswordResetToken(email);
        return new ResponseEntity<>(new MessageDto<>("Password reset token sent successfully.", null), HttpStatus.OK);
    }

    @PutMapping("/reset-password")
    public ResponseEntity<MessageDto<String>> resetPassword(@RequestParam String newPassword, @RequestParam String token, @RequestParam String email) {
        authenticationUserService.resetPassword(email, newPassword, token);
        return new ResponseEntity<>(new MessageDto<>("Password Reset Successfully", null), HttpStatus.OK);

    }
}
