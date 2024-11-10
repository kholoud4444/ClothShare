package com.ntg.backend.dto.requestDto;


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
    private Long requestId;
    private LocalDate date;
    private Request.RequestStatus status;
    private String reason;
    private Long needyId;    // Add needyId
    private Long itemId;     // Add itemId


}