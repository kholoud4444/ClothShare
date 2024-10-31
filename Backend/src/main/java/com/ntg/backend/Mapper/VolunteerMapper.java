package com.ntg.backend.Mapper;

import com.ntg.backend.dto.VolunteerDto;
import com.ntg.backend.entity.Volunteer;
import org.springframework.stereotype.Component;

@Component
public class VolunteerMapper {
    public Volunteer mapperToEntity(VolunteerDto volunteerDto) {
        Volunteer volunteer = new Volunteer();
        volunteer.setPassword(volunteerDto.getPassword());
        volunteer.setLocation(volunteerDto.getLocation());
        volunteer.setGender(volunteerDto.getGender());
        volunteer.setEmail(volunteerDto.getEmail());
        volunteer.setPhone(volunteerDto.getPhone());
        volunteer.setFirstName(volunteerDto.getFirstName());
        volunteer.setLastName(volunteerDto.getLastName());
        volunteer.setBirthDate(volunteerDto.getBirthDate());
        volunteer.setNationalId(volunteerDto.getNationalId());

        return volunteer;
    }

}
