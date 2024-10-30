package com.ntg.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class NeedyDto {
    private Long needyId;
    private String firstName;
    private String lastName;
    private String phone;
    private String location;
    private List<RequestDto> requests;  // Nested list of RequestDTOs
}