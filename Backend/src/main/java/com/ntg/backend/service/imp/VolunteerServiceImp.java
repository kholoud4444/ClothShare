package com.ntg.backend.service.imp;

import com.ntg.backend.Mapper.VolunteerMapper;
import com.ntg.backend.dto.NeedyDto;
import com.ntg.backend.dto.VolunteerDto;
import com.ntg.backend.entity.Volunteer;
import com.ntg.backend.exception.ResourceNotFoundException;
import com.ntg.backend.repository.VolunteerRepo;
import com.ntg.backend.service.VolunteerService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VolunteerServiceImp implements VolunteerService {
    @Autowired
    private VolunteerRepo volunteerRepo;
    @Autowired
    private VolunteerMapper volunteerMapper;


    @Override
    public Volunteer addVolunteer(VolunteerDto volunteerDto) {
        Volunteer volunteer = volunteerMapper.mapperToEntity(volunteerDto);
        return volunteerRepo.save(volunteer);


    }

    @Override
    public Volunteer getVolunteerById(long volunteerId) {

        return volunteerRepo.findById(volunteerId)
                .orElseThrow(()
                        -> new ResourceNotFoundException("User", "id", volunteerId));


    }


    @Override
    public void deleteVolunteerById(long volunteerId) {
        Volunteer volunteer = volunteerRepo.findById(volunteerId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", volunteerId));
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

        Volunteer volunteer = volunteerRepo.findById(volunteerId).orElseThrow(()
                -> new ResourceNotFoundException("User", "id", volunteerId));
        volunteer.setFirstName(volunteerDto.getFirstName());
        volunteer.setLastName(volunteerDto.getLastName());
        volunteer.setEmail(volunteerDto.getEmail());
        volunteer.setPhone(volunteerDto.getPhone());
        volunteer.setGender(volunteerDto.getGender());
        volunteer.setBirthDate(volunteerDto.getBirthDate());
        volunteer.setNationalId(volunteerDto.getNationalId());
        volunteer.setLocation(volunteerDto.getLocation());
        volunteer.setPassword(volunteerDto.getPassword());

        return volunteerRepo.save(volunteer);

    }
}




