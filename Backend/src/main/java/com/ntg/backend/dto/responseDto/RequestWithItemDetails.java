package com.ntg.backend.dto.responseDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RequestWithItemDetails {
    private String type;
    private String size;
    private String state;
    private String genderSuitability;
    private String imageUrl;
    private String description;
    private String status;
    private String requestStatus;
    private String  reason;
    private Date date;
}
