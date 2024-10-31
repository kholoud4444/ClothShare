package com.ntg.backend.Mapper;

import com.ntg.backend.dto.NeedyDto;
import com.ntg.backend.entity.Needy;
import org.springframework.stereotype.Component;

@Component
public class NeedyMapper {
    public Needy Mappertoentity(NeedyDto needy) {
        Needy needy1 = new Needy();
        needy1.setLastName(needy.getLastName());
        needy1.setFirstName(needy.getFirstName());
        needy1.setEmail(needy.getEmail());
        needy1.setPhone(needy.getPhone());
        needy1.setBirthDate(needy.getBirthDate());
        needy1.setPassword(needy.getPassword());
        needy1.setGender(needy.getGender());
        needy1.setLocation(needy.getLocation());
        needy1.setNationalId(needy.getNationalId());
        return needy1;


    }
}
