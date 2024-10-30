package com.ntg.backend.service.imp;


import com.ntg.backend.dto.RequestDto;
import com.ntg.backend.entity.Request;
import com.ntg.backend.repository.RequestRepo;
import com.ntg.backend.service.RequestService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RequestServiceImp implements RequestService {
    @Autowired
    private RequestRepo requestRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public RequestDto createRequest(RequestDto requestDto) {
        Request request = modelMapper.map(requestDto, Request.class);
        Request savedRequest = requestRepo.save(request);
        return modelMapper.map(savedRequest, RequestDto.class);
    }

    @Override
    public RequestDto updateRequest(RequestDto requestDto,long id) {
        Request request = requestRepo.findById(id).get();
        request.setDate(requestDto.getDate());
        request.setStatus(requestDto.getStatus());
        request.setReason(requestDto.getReason());
        Request savedRequest = requestRepo.save(request);
        return modelMapper.map(savedRequest, RequestDto.class);
    }

    @Override
    public List<RequestDto> getAllRequests() {
        List<Request> requests = requestRepo.findAll();
        return requests.stream().map(request -> modelMapper.map(request, RequestDto.class)).collect(Collectors.toList());
    }

    @Override
    public RequestDto getRequestById(long id) {
        Request request = requestRepo.findById(id).get();
        return modelMapper.map(request, RequestDto.class);
    }

    @Override
    public void deleteRequest(long id) {
        Request request =  requestRepo.findById(id).get();
        requestRepo.delete(request);
    }

}
