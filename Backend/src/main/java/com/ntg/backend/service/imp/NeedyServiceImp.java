package com.ntg.backend.service.imp;

import com.ntg.backend.Mapper.NeedyMapper;
import com.ntg.backend.dto.NeedyDto;
import com.ntg.backend.entity.Needy;
import com.ntg.backend.exception.ResourceNotFoundException;
import com.ntg.backend.repository.NeedyRepo;
import com.ntg.backend.service.NeedyService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class NeedyServiceImp implements NeedyService {

    @Autowired
    private NeedyRepo needyRepo;


    @Autowired
    private NeedyMapper needyMapper;

    @Override
    public Needy createNeedy(NeedyDto needydto) {
        // Map the Needy to Volunteer entity
        Needy needy = needyMapper.Mappertoentity(needydto);
        // Save the Needy entity to the database
        Needy savedNeedy =  needyRepo.save(needy);
        return   needy;
    }

    @Override
    public Needy updateNeedy(NeedyDto needyDto,long  id) {
        Needy needy = needyRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Needy not found with id: " + id));
        needy.setFirstName(needyDto.getFirstName());
        needy.setLastName(needyDto.getLastName());
        needy.setEmail(needyDto.getEmail());
        needy.setLocation(needyDto.getLocation());
        needy.setPhone(needyDto.getPhone());
        needy.setGender(needyDto.getGender());
        needy.setNationalId(needyDto.getNationalId());
        needy.setPassword(needyDto.getPassword());
        needy.setBirthDate(needyDto.getBirthDate());
       return needyRepo.save(needy);

    }

    @Override
    public List<Needy> getAllNeedy() {
        List<Needy> needies = needyRepo.findAll();
        if (needies.isEmpty()) {
            throw new ResourceNotFoundException("No needy records found");
        }
        else {
            return needies;
        }
    }

    @Override
    public Needy getNeedyById(long id) {
        Needy needy = needyRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
        return needy;
    }

    @Override
    public void deleteNeedy(long id) {
        Needy needy =  needyRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
        needyRepo.delete(needy);
    }
}
