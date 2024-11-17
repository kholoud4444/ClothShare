package com.ntg.backend.controller;


import com.ntg.backend.dto.requestDto.AuthenticationRequestBody;
import com.ntg.backend.dto.requestDto.RegisterDto;
import com.ntg.backend.dto.responseDto.AuthenticationResponseBody;
import com.ntg.backend.entity.User;
import com.ntg.backend.service.imp.AuthenticationService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/user")
    public User getUser(@RequestAttribute("authenticatedUser") User user) {
        return user;
    }

    @PutMapping("/validate-email-verification-token")
    public String verifyEmail(@RequestParam String token, @RequestAttribute("authenticatedUser") User user) {
        authenticationUserService.validateEmailVerificationToken(token, user.getEmail());
        return "Email verified successfully.";
    }

    @PostMapping("/send-email-verification-token")
    public String sendEmailVerificationToken(@RequestAttribute("authenticatedUser") User user) {
        authenticationUserService.sendEmailVerificationToken(user.getEmail());
        return "Email verification token sent successfully.";
    }

    @PutMapping("/send-password-reset-token")
    public String sendPasswordResetToken(@RequestParam String email) {
        authenticationUserService.sendPasswordResetToken(email);
        return "Password reset token sent successfully.";
    }

    @PutMapping("/reset-password")
    public String resetPassword(@RequestParam String newPassword, @RequestParam String token, @RequestParam String email) {
        authenticationUserService.resetPassword(email, newPassword, token);
        return "Password reset successfully.";
    }
}
