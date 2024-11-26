package com.ntg.backend.service.imp;

import com.ntg.backend.Mapper.UserMapper;
import com.ntg.backend.dto.requestDto.AuthenticationRequestBody;
import com.ntg.backend.dto.requestDto.RegisterDto;
import com.ntg.backend.dto.requestDto.VerifyDto;
import com.ntg.backend.dto.requestDto.verifyResetPasswordCode;
import com.ntg.backend.dto.responseDto.AuthenticationResponseBody;
import com.ntg.backend.dto.responseDto.ResponseMessage;
import com.ntg.backend.entity.User;
import com.ntg.backend.exception.ResourceNotFoundException;
import com.ntg.backend.jwt.JwtService;
import com.ntg.backend.repository.UserRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.LocalDateTime;

@Service
public class AuthenticationService {
    private static final Logger logger = LoggerFactory.getLogger(AuthenticationService.class);
    private final UserRepo userRepository;
    private final UserMapper userMapper; // Repository for Volunteer
    private final int durationInMinutes = 5;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final JwtService jwtService;
    private final EmailService emailService;

    public AuthenticationService(UserRepo userRepository, UserMapper userMapper, BCryptPasswordEncoder bCryptPasswordEncoder, JwtService jwtService, EmailService emailService) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.jwtService = jwtService;
        this.emailService = emailService;
    }

    // Helper method to generate a verification token
    private static String generateVerificationToken() {
        SecureRandom random = new SecureRandom();
        StringBuilder token = new StringBuilder(4);
        for (int i = 0; i < 4; i++) {
            token.append(random.nextInt(10)); // Appending random digit from 0 to 9
        }
        return token.toString();
    }

    // Send email verification token
    public void sendEmailVerificationToken(String email) {
        User user = userRepository.findByEmail(email);
        if (user != null && !user.isEmailVerified()) {
            String emailVerificationToken = generateVerificationToken();
            String hashedToken = bCryptPasswordEncoder.encode(emailVerificationToken);
            user.setEmailVerificationToken(hashedToken);
            user.setEmailVerificationTokenExpiryDate(LocalDateTime.now().plusMinutes(durationInMinutes));
            userRepository.save(user);

            String subject = "Email Verification";
            String body = String.format("Enter this code to verify your email: %s. The code will expire in %s minutes.", emailVerificationToken, durationInMinutes);
            sendEmail(email, subject, body);
        } else {
            throw new IllegalArgumentException("Email verification failed, or email is already verified.");
        }
    }

    // Validate email verification token
    public void validateEmailVerificationToken(VerifyDto verifyDto) {
        User user = userRepository.findByEmail(verifyDto.getEmail());

        // Check if the user was found
        if(user == null){
          throw new ResourceNotFoundException("Email Not Found ");
        }

        // This will throw a null pointer exception if user is null
        System.out.println("email----->" + user.getEmail());

        // Check if user is null again and throw exception
        if (user == null) {
            throw new ResourceNotFoundException("Email Not Found");
        }

        // If user is found and OTP matches and token has not expired
        if (user != null && bCryptPasswordEncoder.matches(verifyDto.getOtp(), user.getEmailVerificationToken())
                && !user.getEmailVerificationTokenExpiryDate().isBefore(LocalDateTime.now())) {
            user.setEmailVerified(true);
            user.setEmailVerificationToken(null);
            user.setEmailVerificationTokenExpiryDate(null);
            userRepository.save(user);
        } else if (user != null && bCryptPasswordEncoder.matches(verifyDto.getOtp(), user.getEmailVerificationToken())
                && user.getEmailVerificationTokenExpiryDate().isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("Email verification token expired.");
        } else {
            throw new IllegalArgumentException("Email verification token failed.");
        }
    }

    // Login user
    public AuthenticationResponseBody login(AuthenticationRequestBody loginRequestBody) {
        User user = userRepository.findByEmail(loginRequestBody.getEmail());
        if (user == null || !bCryptPasswordEncoder.matches(loginRequestBody.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("Invalid credentials.");
        }
        if(!user.isEmailVerified())
        {
            throw new IllegalArgumentException("Email Must Be Verified");
        }
        String token = jwtService.generateToken(loginRequestBody.getEmail());
        return new AuthenticationResponseBody(token, "Authentication succeeded.");
    }

    // Register user and send email verification
    public AuthenticationResponseBody register(RegisterDto registerRequestBody) {
        User user = userMapper.toUser(registerRequestBody);

        // Encode the password before saving
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));

        String emailVerificationToken = generateVerificationToken();
        String hashedToken = bCryptPasswordEncoder.encode(emailVerificationToken);
        user.setEmailVerificationToken(hashedToken);
        user.setEmailVerificationTokenExpiryDate(LocalDateTime.now().plusMinutes(durationInMinutes));
        userRepository.save(user);

        String subject = "Email Verification";
        String body = String.format("Enter this code to verify your email: %s. The code will expire in %s minutes.", emailVerificationToken, durationInMinutes);
        sendEmail(registerRequestBody.getEmail(), subject, body);

        String authToken = jwtService.generateToken(registerRequestBody.getEmail());
        return new AuthenticationResponseBody(authToken, "User registered successfully.");
    }

    // Helper method to send email
    private void sendEmail(String to, String subject, String body) {
        try {
            emailService.sendEmail(to, subject, body);
        } catch (Exception e) {
            logger.error("Error while sending email: {}", e.getMessage());
        }
    }

    // Reset password logic
    public void sendPasswordResetToken(String email) {
        User user = userRepository.findByEmail(email);
        if (user != null) {
            String passwordResetToken = generateVerificationToken();
            String hashedToken = bCryptPasswordEncoder.encode(passwordResetToken);
            user.setPasswordResetToken(hashedToken);
            user.setPasswordResetTokenExpiryDate(LocalDateTime.now().plusMinutes(durationInMinutes));
            userRepository.save(user);

            String subject = "Password Reset";
            String body = String.format("Enter this code to reset your password: %s. The code will expire in %s minutes.", passwordResetToken, durationInMinutes);
            sendEmail(email, subject, body);
        } else {
            throw new IllegalArgumentException("User not found.");
        }
    }

    // Reset user password
    public void resetPassword(String email, String newPassword, String token) {
        User user = userRepository.findByEmail(email);
        if (user != null && bCryptPasswordEncoder.matches(token, user.getPasswordResetToken())
                && !user.getPasswordResetTokenExpiryDate().isBefore(LocalDateTime.now())) {
            user.setPasswordResetToken(null);
            user.setPasswordResetTokenExpiryDate(null);
            user.setPassword(bCryptPasswordEncoder.encode(newPassword));
            userRepository.save(user);

        } else if (user != null && bCryptPasswordEncoder.matches(token, user.getPasswordResetToken())
                && user.getPasswordResetTokenExpiryDate().isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("Password reset token expired.");
        } else {
            throw new IllegalArgumentException("Password reset token failed.");
        }
    }
    public void verifyResetTokenPassword(verifyResetPasswordCode verifyResetPasswordCode) {
        User user = userRepository.findByEmail(verifyResetPasswordCode.getEmail());
        System.out.println();

        if ( !bCryptPasswordEncoder.matches(verifyResetPasswordCode.getResetPasswordCode(), user.getPasswordResetToken()))
        {
            throw new IllegalArgumentException("Invalid reset password code.");

        }
         if ( user.getPasswordResetTokenExpiryDate().isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("Password reset token expired.");
        }
    }
}
