package com.ntg.backend.dto.responseDto;

import com.ntg.backend.dto.requestDto.ItemDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class VolunteerWithItemsDetails extends UserResponseDetails{
    private List<ItemDto> items; // List of items associated with the volunteer
}
