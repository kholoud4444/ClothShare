package com.ntg.backend.service.imp;

import com.ntg.backend.dto.NeedyDto;
import com.ntg.backend.dto.VolunteerDto;
import com.ntg.backend.entity.Needy;
import com.ntg.backend.entity.Volunteer;
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
    private ModelMapper modelMapper ;
    @Override
    public NeedyDto createNeedy(NeedyDto needyDto) {
        // Map the Needy to Volunteer entity
        Needy needy = modelMapper.map(needyDto, Needy.class);
        // Save the Needy entity to the database
        Needy savedNeedy =  needyRepo.save(needy);
        return    modelMapper.map(savedNeedy, NeedyDto.class);
    }




    @Override
    public NeedyDto updateNeedy(NeedyDto needyDto,long  id) {
        Needy needy = needyRepo.findById(id).get();
        needy.setFirstName(needyDto.getFirstName());
        needy.setLastName(needyDto.getLastName());
        needy.setEmail(needyDto.getEmail());
        needy.setLocation(needyDto.getLocation());
        needy.setPhone(needyDto.getPhone());
        needy.setGender(needyDto.getGender());
        needy.setNationalId(needyDto.getNationalId());
        needy.setPassword(needyDto.getPassword());
        needy.setBirthDate(needyDto.getBirthDate());
       Needy savedNeedy = needyRepo.save(needy);
       return    modelMapper.map(savedNeedy, NeedyDto.class);


    }

    @Override
    public List<NeedyDto> getAllNeedy() {
        List<Needy> needies = needyRepo.findAll();
        return needies.stream().map(needy -> modelMapper.map(needy, NeedyDto.class)).collect(Collectors.toList());
    }

    @Override
    public NeedyDto getNeedyById(long id) {
        Needy needy = needyRepo.findById(id).get();
        return    modelMapper.map(needy, NeedyDto.class);
    }

    @Override
    public void deleteNeedy(long id) {
        Needy needy =  needyRepo.findById(id).get();
        needyRepo.delete(needy);


    }
}
