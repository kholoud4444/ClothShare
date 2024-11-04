package com.ntg.backend.Mapper;

import com.ntg.backend.dto.VolunteerDto;
import com.ntg.backend.entity.Volunteer;
import org.springframework.stereotype.Component;

@Component
public class VolunteerMapper {

    public Volunteer mapToEntity(VolunteerDto volunteerDto) {
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
        volunteer.setRole(volunteerDto.getRole());
        return volunteer;
    }

    public void updateEntityFromDto(VolunteerDto volunteerDto, Volunteer volunteer) {
        if (volunteerDto.getPassword() != null) {
            volunteer.setPassword(volunteerDto.getPassword());
        }
        if (volunteerDto.getLocation() != null) {
            volunteer.setLocation(volunteerDto.getLocation());
        }
        if (volunteerDto.getGender() != null) {
            volunteer.setGender(volunteerDto.getGender());
        }
        if (volunteerDto.getEmail() != null) {
            volunteer.setEmail(volunteerDto.getEmail());
        }
        if (volunteerDto.getPhone() != null) {
            volunteer.setPhone(volunteerDto.getPhone());
        }
        if (volunteerDto.getFirstName() != null) {
            volunteer.setFirstName(volunteerDto.getFirstName());
        }
        if (volunteerDto.getLastName() != null) {
            volunteer.setLastName(volunteerDto.getLastName());
        }
        if (volunteerDto.getBirthDate() != null) {
            volunteer.setBirthDate(volunteerDto.getBirthDate());
        }
        if (volunteerDto.getNationalId() != null) {
            volunteer.setNationalId(volunteerDto.getNationalId());
        }
        if (volunteerDto.getRole() != null) {
            volunteer.setRole(volunteerDto.getRole());
        }
    }
}
