package com.ntg.backend.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class VolunteerDto {
    private String firstName;
    private String lastName;
    private String password;
    private String email;
    private String phone;
    private String nationalId;
    private String gender;
    private Date birthDate;
    private String location;


}
