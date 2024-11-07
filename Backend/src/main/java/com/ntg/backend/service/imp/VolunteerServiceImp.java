package com.ntg.backend.service.imp;

import com.ntg.backend.dto.requestDto.ItemDto;
import com.ntg.backend.dto.responseDto.VolunteerResponseDetails;
import com.ntg.backend.Mapper.VolunteerMapper;
import com.ntg.backend.dto.requestDto.VolunteerDto;
import com.ntg.backend.dto.responseDto.VolunteerWithItemsDetails;
import com.ntg.backend.entity.Volunteer;
import com.ntg.backend.exception.ResourceNotFoundException;
import com.ntg.backend.repository.VolunteerRepo;
import com.ntg.backend.service.VolunteerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VolunteerServiceImp implements VolunteerService {

    private final VolunteerRepo volunteerRepo;
    private final VolunteerMapper volunteerMapper;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public VolunteerServiceImp(VolunteerRepo volunteerRepo, VolunteerMapper volunteerMapper, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.volunteerRepo = volunteerRepo;
        this.volunteerMapper = volunteerMapper;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }





    @Override
    public VolunteerWithItemsDetails getVolunteerWithItemsDetails(long volunteerId) {
        Volunteer volunteer =  volunteerRepo.findById(volunteerId)
                .orElseThrow(() -> new ResourceNotFoundException("Volunteer", "id", volunteerId));
        return volunteerMapper.toVolunteerWithItemsDetails(volunteer);


    }

    @Override
    public List<VolunteerWithItemsDetails> getAllVolunteersWithItems() {
        List<Volunteer> volunteers = volunteerRepo.findAll();

        return volunteers.stream()
                .map(volunteerMapper::toVolunteerWithItemsDetails)
                .collect(Collectors.toList());
    }

    @Override
    public List<ItemDto> getAllItemsByVolunteerId(long volunteerId) {
       Volunteer volunteer = volunteerRepo.findById(volunteerId)
               .orElseThrow(() -> new ResourceNotFoundException("Volunteer", "id", volunteerId));
        return volunteerMapper.toItemDtoList(volunteer);
    }


}
