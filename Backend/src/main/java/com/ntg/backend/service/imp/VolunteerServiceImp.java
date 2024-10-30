package com.ntg.backend.service.imp;

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
    private ModelMapper modelMapper ;
    @Override
    public VolunteerDto addVolunteer(VolunteerDto volunteerDto) {
        // Map the VolunteerDto to Volunteer entity
        Volunteer volunteer = modelMapper.map(volunteerDto, Volunteer.class);
        // Save the volunteer entity to the database
        Volunteer savedVolunteer =  volunteerRepo.save(volunteer);

        return    modelMapper.map(savedVolunteer, VolunteerDto.class);
    }

    @Override
    public VolunteerDto getVolunteerById(long volunteerId) {

        Volunteer volunteer = volunteerRepo.findById(volunteerId)
                .orElseThrow(()
                -> new ResourceNotFoundException("User", "id", volunteerId));
        return    modelMapper.map(volunteer, VolunteerDto.class);



        }




    @Override
    public void deleteVolunteerById(long volunteerId) {
        Volunteer volunteer =  volunteerRepo.findById(volunteerId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", volunteerId));
        volunteerRepo.delete(volunteer);
    }

    @Override
    public List<VolunteerDto> getAllVolunteers() {
        List<Volunteer> volunteers = volunteerRepo.findAll();
        if (volunteers.isEmpty()) {
            throw new ResourceNotFoundException("No Volunteer records found");
        }
        else {
            return volunteers.stream().map(volunteer ->
                    modelMapper.map(volunteer, VolunteerDto.class)).collect(Collectors.toList());
        }

    }

    @Override
    public VolunteerDto updateVolunteer(VolunteerDto volunteerDto, long volunteerId) {

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

         Volunteer savedVolunteer = volunteerRepo.save(volunteer);
            return modelMapper.map(savedVolunteer,VolunteerDto.class);

        }



}
