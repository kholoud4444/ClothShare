package com.ntg.backend.dto.responseDto;

import com.ntg.backend.entity.Needy;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RequestWithNeedyDetails {
    private String requestStatus;
    private String reason;
    private LocalDate date;
    private NeedyInfo needyInfo;
}
