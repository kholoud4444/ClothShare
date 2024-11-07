package com.ntg.backend.dto.responseDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDetails {
    private Long userId;
    private String firstName;
    private String lastName;
    private String role;
    private String email;
    private String phone;
    private String nationalId;
    private String gender;
    private LocalDate birthDate;
    private String location;
}
