package com.ntg.backend.controller;


import com.ntg.backend.dto.requestDto.*;
import com.ntg.backend.dto.responseDto.AuthenticationResponseBody;
import com.ntg.backend.entity.User;
import com.ntg.backend.service.imp.AuthenticationService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/authentication")
@CrossOrigin("http://localhost:4200")

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

    @PutMapping("/validateEmailVerificationToken")
    public  ResponseEntity<MessageDto<String>> verifyEmail(@RequestBody VerifyDto verifyDto) {
        authenticationUserService.validateEmailVerificationToken(verifyDto);
        return new ResponseEntity<>(new MessageDto<>("Email verified successfully", null), HttpStatus.OK);
    }

    @PutMapping("/sendEmailVerificationToken")
    public ResponseEntity<MessageDto<String>>  sendEmailVerificationToken(@RequestBody String email) {
        authenticationUserService.sendEmailVerificationToken(email);
        return new ResponseEntity<>(new MessageDto<>("Email verification token sent successfully", null), HttpStatus.OK);
    }

    @PutMapping("/sendPasswordResetToken")
    public  ResponseEntity<MessageDto<String>> sendPasswordResetToken(@RequestBody String email) {
        authenticationUserService.sendPasswordResetToken(email);
        return new ResponseEntity<>(new MessageDto<>("Password reset token sent successfully.", null), HttpStatus.OK);
    }

//    @PutMapping("/resetPassword")
//    public ResponseEntity<MessageDto<String>> resetPassword(@RequestParam String newPassword, @RequestParam String token, @RequestParam String email) {
//        authenticationUserService.resetPassword(email, newPassword, token);
//        return new ResponseEntity<>(new MessageDto<>("Password Reset Successfully", null), HttpStatus.OK);
//
//    }
    @PutMapping("/verifyResetTokenPassword")
    public ResponseEntity<MessageDto<String>> verifyResetTokenPassword
            (@RequestBody verifyResetPasswordCode verifyResetPasswordCode) {
        authenticationUserService.verifyResetTokenPassword(verifyResetPasswordCode);
        return new ResponseEntity<>(new MessageDto<>("Otp Successfully", null), HttpStatus.OK);

    }
       @PutMapping("/updateNewPassword")
    public ResponseEntity<MessageDto<String>> resetPassword(@RequestBody CreateNewPassword createNewPassword) {
        authenticationUserService.createNewPassword(createNewPassword);
        return new ResponseEntity<>(new MessageDto<>("Password Reset Successfully", null), HttpStatus.OK);

    }
    @PostMapping("/contactus")
    public ResponseEntity <MessageDto<ContactUs>> loginPage( @RequestBody ContactUs contactUs) {
        return new ResponseEntity<>(new MessageDto<>("email sent Successfully", contactUs), HttpStatus.OK);
    }

}