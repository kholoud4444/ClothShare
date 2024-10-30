package com.ntg.backend.service;

import com.ntg.backend.dto.VolunteerDto;

import java.util.List;

public interface VolunteerService {
    VolunteerDto addVolunteer(VolunteerDto volunteerDto);
    VolunteerDto getVolunteerById(long volunteerId);
    void deleteVolunteerById(long volunteerId);
    List<VolunteerDto> getAllVolunteers();
    VolunteerDto updateVolunteer(VolunteerDto volunteerDto, long volunteerId);
}
