package com.ntg.backend.entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@MappedSuperclass
public abstract class User {
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