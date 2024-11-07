package com.ntg.backend.Mapper;

import com.ntg.backend.dto.requestDto.NeedyDto;
import com.ntg.backend.entity.Needy;
import org.springframework.stereotype.Component;

@Component
public class NeedyMapper {
    public Needy mapToEntity(NeedyDto needyDto) {
        Needy needy = new Needy();
        needy.setFirstName(needyDto.getFirstName());
        needy.setLastName(needyDto.getLastName());
        needy.setEmail(needyDto.getEmail());
        needy.setPhone(needyDto.getPhone());
        needy.setBirthDate(needyDto.getBirthDate());
        needy.setPassword(needyDto.getPassword());
        needy.setGender(needyDto.getGender());
        needy.setLocation(needyDto.getLocation());
        needy.setNationalId(needyDto.getNationalId());
        return needy;
    }

    public void updateEntityFromDto(NeedyDto needyDto, Needy needy) {
        if (needyDto.getFirstName() != null) {
            needy.setFirstName(needyDto.getFirstName());
        }
        if (needyDto.getLastName() != null) {
            needy.setLastName(needyDto.getLastName());
        }
        if (needyDto.getEmail() != null) {
            needy.setEmail(needyDto.getEmail());
        }
        if (needyDto.getPhone() != null) {
            needy.setPhone(needyDto.getPhone());
        }
        if (needyDto.getBirthDate() != null) {
            needy.setBirthDate(needyDto.getBirthDate());
        }
        if (needyDto.getPassword() != null) {
            needy.setPassword(needyDto.getPassword());
        }
        if (needyDto.getGender() != null) {
            needy.setGender(needyDto.getGender());
        }
        if (needyDto.getLocation() != null) {
            needy.setLocation(needyDto.getLocation());
        }
        if (needyDto.getNationalId() != null) {
            needy.setNationalId(needyDto.getNationalId());
        }
    }
}
