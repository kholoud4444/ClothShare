package com.ntg.backend.service.imp;

import com.ntg.backend.Mapper.NeedyMapper;
import com.ntg.backend.Mapper.RequestMapper;
import com.ntg.backend.dto.ResponsePagination.PageDto;
import com.ntg.backend.dto.responseDto.RequestWithItemDetails;
import com.ntg.backend.entity.Needy;
import com.ntg.backend.entity.Request;
import com.ntg.backend.entity.User;
import com.ntg.backend.exception.ResourceNotFoundException;
import com.ntg.backend.repository.NeedyRepo;
import com.ntg.backend.repository.RequestRepo;
import com.ntg.backend.service.NeedyService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class NeedyServiceImp implements NeedyService {

    private final NeedyRepo needyRepo;
    private final RequestRepo requestRepo;
    private final NeedyMapper needyMapper;
    private  final RequestMapper requestMapper;

    @Autowired
    public NeedyServiceImp(NeedyRepo needyRepo, RequestRepo requestRepo, NeedyMapper needyMapper, RequestMapper requestMapper) {
        this.needyRepo = needyRepo;
        this.requestRepo = requestRepo;
        this.needyMapper = needyMapper;
        this.requestMapper = requestMapper;
    }

    @Override
    public PageDto<RequestWithItemDetails> getAllRequestDetailsByNeedyId(long needyId, int pageNo, int pageSize) {
        Needy needyUser = needyRepo.findById(needyId)
                .orElseThrow(() -> new ResourceNotFoundException("Needy", "id", needyId));

        // Create a pageable object
        Pageable pageable = PageRequest.of(pageNo, pageSize);

        // Get the requests with pagination
        Page<Request> requestPage = requestRepo.findByNeedy(needyUser, pageable);

        // Map each Request to RequestWithItemDetails DTO
        List<RequestWithItemDetails> requestDtos = requestPage.getContent().stream()
                .map(requestMapper::mapToRequestWithItemDetails)
                .collect(Collectors.toList());

        // Create and return the PageDto
        return new PageDto<>(
                requestDtos,
                requestPage.getTotalElements(),
                requestPage.getTotalPages(),
                requestPage.getNumber(),
                requestPage.getSize(),
                requestPage.isLast()
        );
    }

    @Override
    public PageDto<RequestWithItemDetails> getAllRequestDetailsForAllNeedies(int pageNo,int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<Needy> allNeedies = needyRepo.findAll(pageable);

        List<Needy>listOfNeedy = allNeedies.getContent();
        List<RequestWithItemDetails> requestDetails = allNeedies.stream()
                .flatMap(needy -> needy.getRequests().stream()) // Flatten the list of lists of requests
                .map(requestMapper::mapToRequestWithItemDetails)
                .collect(Collectors.toList());

        return new PageDto<>(
                requestDetails,
                allNeedies.getTotalElements(),
                allNeedies.getTotalPages(),
                allNeedies.getNumber(),
                allNeedies.getSize(),
                allNeedies.isLast()
        );
    }



}
