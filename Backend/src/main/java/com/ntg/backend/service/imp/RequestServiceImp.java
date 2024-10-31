package com.ntg.backend.service.imp;


import com.ntg.backend.Mapper.RequestMapper;
import com.ntg.backend.dto.RequestDto;
import com.ntg.backend.entity.Item;
import com.ntg.backend.entity.Needy;
import com.ntg.backend.entity.Request;
import com.ntg.backend.exception.ResourceNotFoundException;
import com.ntg.backend.repository.ItemRepo;
import com.ntg.backend.repository.NeedyRepo;
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
    private RequestMapper requestMapper;
    @Autowired
    private NeedyRepo needyRepo;
    @Autowired
    private ItemRepo itemRepo;
    @Override
    public Request createRequest(RequestDto requestDto,Long id_needy,Long id_items) {
        Item item = itemRepo.findById(id_items).get();
        Needy needy = needyRepo.findById(id_needy).get();
        Request request = requestMapper.MaptoEntity(requestDto);
        needy.getRequests().add(request);
        request.setNeedy(needy);

        item.getRequests().add(request);
        request.setItem(item);
        itemRepo.save(item);



      return request;
    }

//    @Override
//    public RequestDto updateRequest(RequestDto requestDto,long id) {
//        Request request = requestRepo.findById(id).orElseThrow(()
//                -> new ResourceNotFoundException("User", "id", id));
//        request.setDate(requestDto.getDate());
//        request.setStatus(requestDto.getStatus());
//        request.setReason(requestDto.getReason());
//        Request savedRequest = requestRepo.save(request);
//        return modelMapper.map(savedRequest, RequestDto.class);
//    }

    @Override
    public List<Request> getAllRequests() {
        List<Request> requests = requestRepo.findAll();
        return requests;
    }

    @Override
    public Request getRequestById(long id) {
        Request request = requestRepo.findById(id).orElseThrow(()
                -> new ResourceNotFoundException("User", "id", id));
        return request;
    }

    @Override
    public void deleteRequest(long id) {
        Request request =  requestRepo.findById(id).orElseThrow(()
                -> new ResourceNotFoundException("User", "id", id));
        requestRepo.delete(request);
    }

}
