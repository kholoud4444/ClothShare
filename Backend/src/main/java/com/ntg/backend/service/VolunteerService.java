package com.ntg.backend.service;

import com.ntg.backend.dto.VolunteerDto;
import com.ntg.backend.entity.Volunteer;

import java.util.List;

public interface VolunteerService {
    Volunteer addVolunteer(VolunteerDto volunteerDto);
   Volunteer getVolunteerById(long volunteerId);
   void deleteVolunteerById(long volunteerId);
    List<Volunteer> getAllVolunteers();
   Volunteer updateVolunteer(VolunteerDto volunteerDto, long volunteerId);
}
