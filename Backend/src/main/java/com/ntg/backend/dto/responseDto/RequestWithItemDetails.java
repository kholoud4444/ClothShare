package com.ntg.backend.dto.responseDto;

import com.ntg.backend.dto.requestDto.ItemDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RequestWithItemDetails {
    private String requestStatus;
    private String  reason;
    private LocalDate date;
    private ItemDto itemData;
    private long needyId;
    private long requestId;
}
