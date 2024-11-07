package com.ntg.backend.dto.requestDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class RegistrationDto {
    private String firstName;
    private String lastName;
    private String password;
    private String email;
    private String phone;
    private String nationalId;
    private String gender;
    private LocalDate birthDate;
    private String location;
    private String role;
}
