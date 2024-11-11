package com.ntg.backend.service.imp;

import com.ntg.backend.Mapper.NeedyMapper;
import com.ntg.backend.Mapper.RequestMapper;
import com.ntg.backend.dto.requestDto.NeedyDto;
import com.ntg.backend.dto.responseDto.NeedyRequestDetails;
import com.ntg.backend.dto.responseDto.RequestWithItemDetails;
import com.ntg.backend.entity.Needy;
import com.ntg.backend.exception.ResourceNotFoundException;
import com.ntg.backend.repository.NeedyRepo;
import com.ntg.backend.service.NeedyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class NeedyServiceImp implements NeedyService {

    private final NeedyRepo needyRepo;
    private final NeedyMapper needyMapper;
    private  final RequestMapper requestMapper;

    @Autowired
    public NeedyServiceImp(NeedyRepo needyRepo, NeedyMapper needyMapper, RequestMapper requestMapper) {
        this.needyRepo = needyRepo;
        this.needyMapper = needyMapper;
        this.requestMapper = requestMapper;
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
    public List<RequestWithItemDetails> getAllRequestDetailsByNeedyId(long needyId) {
     Needy needyUser =  needyRepo.findById(needyId).orElseThrow(() -> new ResourceNotFoundException("Needy", "id", needyId));
        // Map each Request to a RequestWithItemDetails DTO and collect them into a list
        return needyUser.getRequests().stream()
                .map(requestMapper::mapToRequestWithItemDetails)
                .collect(Collectors.toList());
    }


}
