package com.ntg.backend.service.imp;

import com.ntg.backend.Mapper.VolunteerMapper;
import com.ntg.backend.dto.VolunteerDto;
import com.ntg.backend.entity.Volunteer;
import com.ntg.backend.exception.ResourceNotFoundException;
import com.ntg.backend.repository.VolunteerRepo;
import com.ntg.backend.service.VolunteerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VolunteerServiceImp implements VolunteerService {

    private final VolunteerRepo volunteerRepo;
    private final VolunteerMapper volunteerMapper;

    @Autowired
    public VolunteerServiceImp(VolunteerRepo volunteerRepo, VolunteerMapper volunteerMapper) {
        this.volunteerRepo = volunteerRepo;
        this.volunteerMapper = volunteerMapper;
    }

    @Override
    public Volunteer addVolunteer(VolunteerDto volunteerDto) {
        Volunteer volunteer = volunteerMapper.mapToEntity(volunteerDto);
        return volunteerRepo.save(volunteer);
    }

    @Override
    public Volunteer getVolunteerById(long volunteerId) {
        return volunteerRepo.findById(volunteerId)
                .orElseThrow(() -> new ResourceNotFoundException("Volunteer", "id", volunteerId));
    }

    @Override
    public void deleteVolunteerById(long volunteerId) {
        Volunteer volunteer = volunteerRepo.findById(volunteerId)
                .orElseThrow(() -> new ResourceNotFoundException("Volunteer", "id", volunteerId));
        volunteerRepo.delete(volunteer);
    }

    @Override
    public List<Volunteer> getAllVolunteers() {
        List<Volunteer> volunteers = volunteerRepo.findAll();
        if (volunteers.isEmpty()) {
            throw new ResourceNotFoundException("No Volunteer records found");
        }
        return volunteers;
    }

    @Override
    public Volunteer updateVolunteer(VolunteerDto volunteerDto, long volunteerId) {
        Volunteer volunteer = volunteerRepo.findById(volunteerId)
                .orElseThrow(() -> new ResourceNotFoundException("Volunteer", "id", volunteerId));

        // Update fields from VolunteerDto
        volunteerMapper.updateEntityFromDto(volunteerDto, volunteer);

        return volunteerRepo.save(volunteer);
    }
}
