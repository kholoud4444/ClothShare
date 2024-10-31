package com.ntg.backend.service.imp;

import com.ntg.backend.Mapper.NeedyMapper;
import com.ntg.backend.dto.NeedyDto;
import com.ntg.backend.entity.Needy;
import com.ntg.backend.exception.ResourceNotFoundException;
import com.ntg.backend.repository.NeedyRepo;
import com.ntg.backend.service.NeedyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NeedyServiceImp implements NeedyService {

    private final NeedyRepo needyRepo;
    private final NeedyMapper needyMapper;

    @Autowired
    public NeedyServiceImp(NeedyRepo needyRepo, NeedyMapper needyMapper) {
        this.needyRepo = needyRepo;
        this.needyMapper = needyMapper;
    }

    @Override
    public Needy createNeedy(NeedyDto needyDto) {
        Needy needy = needyMapper.mapToEntity(needyDto);
        return needyRepo.save(needy);
    }

    @Override
    public Needy updateNeedy(NeedyDto needyDto, long id) {
        Needy needy = needyRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Needy", "id", id));

        // Update fields from NeedyDto
        needyMapper.updateEntityFromDto(needyDto, needy);

        return needyRepo.save(needy);
    }

    @Override
    public List<Needy> getAllNeedy() {
        List<Needy> needies = needyRepo.findAll();
        if (needies.isEmpty()) {
            throw new ResourceNotFoundException("No needy records found");
        }
        return needies;
    }

    @Override
    public Needy getNeedyById(long id) {
        return needyRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Needy", "id", id));
    }

    @Override
    public void deleteNeedy(long id) {
        Needy needy = needyRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Needy", "id", id));
        needyRepo.delete(needy);
    }
}
