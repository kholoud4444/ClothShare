package com.ntg.backend.dto;


import com.ntg.backend.entity.Request;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class RequestDto {
//    private Long requestId;
    private Long needyId; // Optional: include needy ID if needed
    private Long itemId;  // Optional: include item ID if needed
    private LocalDate date;
    private Request.RequestStatus status;
    private String reason;
}