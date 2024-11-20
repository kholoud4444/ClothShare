package com.ntg.backend.dto.responseDto;

import com.ntg.backend.dto.requestDto.ItemDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RequestWithItemDetails {
    private String requestStatus;
    private String  reason;
    private LocalDate date;
    private ItemDto itemData;
    private Long NeedyId;
    private Long RequestId;
}
