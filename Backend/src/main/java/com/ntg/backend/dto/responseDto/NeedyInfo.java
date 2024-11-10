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
public class NeedyInfo {
    private Long userId;
    private String firstName;
    private String lastName;
    private String phone;
    private String gender;
    private String location;

}
