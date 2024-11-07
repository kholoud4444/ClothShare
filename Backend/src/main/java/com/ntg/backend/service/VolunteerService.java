package com.ntg.backend.service;

import com.ntg.backend.dto.requestDto.ItemDto;
import com.ntg.backend.dto.responseDto.VolunteerResponseDetails;
import com.ntg.backend.dto.requestDto.VolunteerDto;
import com.ntg.backend.dto.responseDto.VolunteerWithItemsDetails;
import com.ntg.backend.entity.Volunteer;

import java.util.List;

public interface VolunteerService {
    //    Volunteer addVolunteer(VolunteerDto volunteerDto);

    VolunteerWithItemsDetails getVolunteerWithItemsDetails(long volunteerId);
    List<VolunteerWithItemsDetails> getAllVolunteersWithItems();
    List<ItemDto> getAllItemsByVolunteerId(long volunteerId);
}
