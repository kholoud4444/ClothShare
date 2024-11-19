package com.ntg.backend.service.imp;

import com.ntg.backend.Mapper.ItemMapper;
import com.ntg.backend.dto.ResponsePagination.PageDto;
import com.ntg.backend.dto.requestDto.ItemDto;
import com.ntg.backend.Mapper.VolunteerMapper;
import com.ntg.backend.dto.responseDto.VolunteerWithItemsDetails;
import com.ntg.backend.entity.Item;
import com.ntg.backend.entity.Volunteer;
import com.ntg.backend.exception.ResourceNotFoundException;
import com.ntg.backend.repository.ItemRepo;
import com.ntg.backend.repository.VolunteerRepo;
import com.ntg.backend.service.VolunteerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Service;


@Service
public class VolunteerServiceImp implements VolunteerService {

    private final VolunteerRepo volunteerRepo;
    private final VolunteerMapper volunteerMapper;
    private final ItemRepo itemRepo;
    private final ItemMapper itemMapper;

    @Autowired
    public VolunteerServiceImp(VolunteerRepo volunteerRepo, VolunteerMapper volunteerMapper, ItemRepo itemRepo, ItemMapper itemMapper) {
        this.volunteerRepo = volunteerRepo;
        this.volunteerMapper = volunteerMapper;
        this.itemRepo = itemRepo;
        this.itemMapper = itemMapper;
    }

    @Override
    public VolunteerWithItemsDetails getVolunteerWithItemsDetails(long volunteerId) {
        Volunteer volunteer =  volunteerRepo.findById(volunteerId)
                .orElseThrow(() -> new ResourceNotFoundException("Volunteer", "id", volunteerId));
        return volunteerMapper.toVolunteerWithItemsDetails(volunteer);
    }

    @Override
    public PageDto<VolunteerWithItemsDetails> getAllVolunteersWithItems(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<Volunteer> volunteers = volunteerRepo.findAll(pageable);
        return volunteerMapper.volunteerPageToDto(volunteers);
    }

    @Override
    public PageDto<ItemDto> getAllItemsByVolunteerId(long volunteerId,int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
       Volunteer volunteer = volunteerRepo.findById(volunteerId).orElseThrow(() ->
               new ResourceNotFoundException("Volunteer", "id", volunteerId));
        Page<Item> itemPage = itemRepo.findByVolunteer(volunteer, pageable);
        return itemMapper.itemDtoPageDto(itemPage);
    }
}
