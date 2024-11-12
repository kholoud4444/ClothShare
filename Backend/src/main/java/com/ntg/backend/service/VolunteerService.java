package com.ntg.backend.service;

import com.ntg.backend.dto.ResponsePagination.PageDto;
import com.ntg.backend.dto.requestDto.ItemDto;
import com.ntg.backend.dto.responseDto.VolunteerWithItemsDetails;

import java.util.List;

public interface VolunteerService {
    VolunteerWithItemsDetails getVolunteerWithItemsDetails(long volunteerId);
    PageDto<VolunteerWithItemsDetails> getAllVolunteersWithItems(int pageNo, int pageSize);
    PageDto<ItemDto> getAllItemsByVolunteerId(long volunteerId,int pageNo, int pageSize);
}
