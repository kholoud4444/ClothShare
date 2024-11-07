package com.ntg.backend.service.imp;

import com.ntg.backend.dto.responseDto.RequestWithItemDetails;
import com.ntg.backend.Mapper.RequestMapper;
import com.ntg.backend.dto.requestDto.RequestDto;
import com.ntg.backend.entity.Item;
import com.ntg.backend.entity.Needy;
import com.ntg.backend.entity.Request;
import com.ntg.backend.exception.ResourceNotFoundException;
import com.ntg.backend.repository.ItemRepo;
import com.ntg.backend.repository.NeedyRepo;
import com.ntg.backend.repository.RequestRepo;
import com.ntg.backend.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RequestServiceImp implements RequestService {

    private final RequestRepo requestRepo;
    private final RequestMapper requestMapper;
    private final NeedyRepo needyRepo;
    private final ItemRepo itemRepo;

    @Autowired
    public RequestServiceImp(RequestRepo requestRepo, RequestMapper requestMapper, NeedyRepo needyRepo, ItemRepo itemRepo) {
        this.requestRepo = requestRepo;
        this.requestMapper = requestMapper;
        this.needyRepo = needyRepo;
        this.itemRepo = itemRepo;
    }

    @Override
    public Request createRequest(RequestDto requestDto) {
        Item item = itemRepo.findById(requestDto.getItemId())
                .orElseThrow(() -> new ResourceNotFoundException("Item", "id", requestDto.getItemId()));
        Needy needy = needyRepo.findById(requestDto.getNeedyId())
                .orElseThrow(() -> new ResourceNotFoundException("Needy", "id", requestDto.getNeedyId()));

        Request request = requestMapper.mapToEntity(requestDto);
        needy.getRequests().add(request);
        request.setNeedy(needy);

        item.getRequests().add(request);
        request.setItem(item);

        return requestRepo.save(request);
    }


    @Override
    public Request updateRequest(RequestDto requestDto, long id) {
        Request request = requestRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Request", "id", id));

        // Use mapper to update fields from DTO
//        requestMapper.updateEntityFromDto(requestDto, request);

        return requestRepo.save(request);
    }

    @Override
    public List<Request> getAllRequests() {
        return requestRepo.findAll();
    }

    @Override
    public Request getRequestById(long id) {
        return requestRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Request", "id", id));
    }


    @Override
    public void deleteRequest(long id) {
        Request request = requestRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Request", "id", id));
        requestRepo.delete(request);
    }

    @Override
    public List<Request> getAllRequestsByNeedyId(long needyId) {
        // Fetch the Needy user by ID
        Needy needy = needyRepo.findById(needyId)
                .orElseThrow(() -> new ResourceNotFoundException("Needy", "id", needyId));

        // Fetch all requests associated with the needy user
        List<Request> requests = needy.getRequests();


        // Check if there are no requests found
        if (requests == null || requests.isEmpty()) {
            throw new ResourceNotFoundException("No requests found for Needy with ID: " + needyId);
        }


        // Map each Request entity to RequestDto
     // Collect results to a list

        return requests;
    }

    @Override
    public RequestWithItemDetails getRequestWithItemDetails(long requestId) {
        Request request = requestRepo.findById(requestId) .orElseThrow(() ->
                new ResourceNotFoundException("request", "id", requestId));

        return requestMapper.mapToRequestWithItemDetails(request);
    }
}
