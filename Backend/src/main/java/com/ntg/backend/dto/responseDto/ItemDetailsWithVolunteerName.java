package com.ntg.backend.dto.responseDto;

import com.ntg.backend.dto.requestDto.ItemDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ItemDetailsWithVolunteerName {
    ItemDto item;
    String volunteerName;
    Long ItemId;
}
