package com.ntg.backend.service;

import com.ntg.backend.dto.requestDto.ItemDto;
import com.ntg.backend.dto.responseDto.VolunteerWithItemsDetails;

import java.util.List;

public interface VolunteerService {
    VolunteerWithItemsDetails getVolunteerWithItemsDetails(long volunteerId);
    List<VolunteerWithItemsDetails> getAllVolunteersWithItems();
    List<ItemDto> getAllItemsByVolunteerId(long volunteerId);
}
