package com.ntg.backend.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class RequestDto {
//    private Long requestId;
    private Long needyId; // Optional: include needy ID if needed
    private Long itemId;  // Optional: include item ID if needed
    private Date date;
    private String status;
    private String reason;
}