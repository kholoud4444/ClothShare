package com.ntg.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class NeedyDto {
    private String firstName;
    private String lastName;
    private String password;
    private String email;
    private String phone;
    private String nationalId;
    private String gender;
    private LocalDate birthDate;
    private String location;
}