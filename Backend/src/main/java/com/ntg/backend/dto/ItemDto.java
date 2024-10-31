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
public class ItemDto {
    private String type;
    private String size;
    private String state;
    private String genderSuitability;
    private String imgUrl;
    private String description;
    private String status;
    private Long volunteerId; // Optional: include volunteer ID if needed
    // Nested list of RequestDTOs
}
