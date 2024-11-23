package com.ntg.backend.service.imp;

import com.ntg.backend.dto.ResponsePagination.PageDto;
import com.ntg.backend.dto.requestDto.MessageDto;
import com.ntg.backend.dto.requestDto.requestStatusDto;
import com.ntg.backend.dto.responseDto.RequestWithItemDetails;
import com.ntg.backend.Mapper.RequestMapper;
import com.ntg.backend.dto.requestDto.RequestDto;
import com.ntg.backend.dto.responseDto.RequestWithNeedyDetails;
import com.ntg.backend.entity.Item;
import com.ntg.backend.entity.Needy;
import com.ntg.backend.entity.Request;
import com.ntg.backend.exception.ResourceNotFoundException;
import com.ntg.backend.repository.ItemRepo;
import com.ntg.backend.repository.NeedyRepo;
import com.ntg.backend.repository.RequestRepo;
import com.ntg.backend.service.RequestService;
import com.sun.jdi.request.DuplicateRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    public RequestWithItemDetails createRequest(RequestDto requestDto) {
        Needy needyUser = needyRepo.findById(requestDto.getNeedyId())
                .orElseThrow(() -> new ResourceNotFoundException("Needy", "id", requestDto.getNeedyId()));

        Item item = itemRepo.findById(requestDto.getItemId())
                .orElseThrow(() -> new ResourceNotFoundException("Item", "id", requestDto.getItemId()));

        //Check if a request with the same Needy and Item already exists
        Optional<Request> existingRequest = requestRepo.findByNeedyAndItem(needyUser, item);
        if (existingRequest.isPresent()) {
            throw new DuplicateRequestException("A request for this item by the same needy user already exists.");
        }

        Request request = requestMapper.mapToRequestEntity(requestDto);
        request.setNeedy(needyUser);
        request.setItem(item);

        Request savedRequest = requestRepo.save(request);
        return requestMapper.mapToRequestWithItemDetails(savedRequest);
    }

    @Override
    public RequestDto getRequestById(long id) {
        Request request = requestRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Request", "id", id));
        return requestMapper.mapToRequestDto(request);
    }

    @Override
    public RequestDto updateRequest(RequestDto requestDto, long id) {
        Request request = requestRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Request", "id", id));
        requestMapper.updateEntityFromDto(requestDto, request);
        return requestMapper.mapToRequestDto(requestRepo.save(request));
    }

    @Override
    public PageDto<RequestDto> getAllRequests(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<Request> requests = requestRepo.findAll(pageable);
        return requestMapper.requestDtoPageDto(requests);
    }

    @Override
    public PageDto<RequestWithItemDetails> getRequestsByItemId(Long itemId, int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);

        // Fetch paginated requests for the specified itemId
        Page<Request> requestsPage = requestRepo.findByItem_ItemId(itemId, pageable);

        if (requestsPage.isEmpty()) {
            throw new ResourceNotFoundException("Request", "Item id", itemId);
        }

        // Map each Request entity to RequestWithItemDetails DTO
        List<RequestWithItemDetails> requestWithItemDetailsList = requestsPage.getContent().stream()
                .map(requestMapper::mapToRequestWithItemDetails)
                .collect(Collectors.toList());

        // Return a new PageDto with the mapped content and pagination details
        return new PageDto<>(
                requestWithItemDetailsList,
                requestsPage.getTotalElements(),
                requestsPage.getTotalPages(),
                requestsPage.getNumber(),
                requestsPage.getSize(),
                requestsPage.isLast()
        );
    }

    @Override
    public void deleteRequest(long id) {
        Request request = requestRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Request", "id", id));
        requestRepo.delete(request);
    }

    @Override
    public RequestWithItemDetails getRequestWithItemDetails(long requestId) {
        Request request = requestRepo.findById(requestId).orElseThrow(() -> new ResourceNotFoundException("request", "id", requestId));
        return requestMapper.mapToRequestWithItemDetails(request);
    }

    @Override
    public MessageDto<requestStatusDto> changeRequestStatus(requestStatusDto requestStatusDto, long id) {
        Request existingRequest = requestRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("request", "id", id));

        // Check if the status from the DTO is different from the existing status
        if (requestStatusDto.getStatus() != existingRequest.getStatus()) {
            // Update the status if different and save to database
            existingRequest.setStatus(requestStatusDto.getStatus());
            requestRepo.save(existingRequest);

//            currentRequest.setNeedyId(existingRequest.getNeedy().getUserId());// Save the updated item status
            return new MessageDto<requestStatusDto>("request updated succesffully",requestStatusDto );
        } else {
//            RequestDto currentRequest= requestMapper.mapToRequestDto(existingRequest);// Save the updated item status
//            String message = "Item status is already " + existingRequest.getStatus() + "; no changes made.";
            return new MessageDto<>("request status is already",requestStatusDto );
        }
    }


    @Override
    public PageDto<RequestWithNeedyDetails> requestsWithNeedyDetails(long itemId, int pageNo, int pageSize) {
        // Check if the item exists
        Item item = itemRepo.findById(itemId).orElseThrow(() -> new ResourceNotFoundException("Item", "id", itemId));

        // Fetch paginated requests for the given item
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<Request> requestsPage = requestRepo.findByItem(item, pageable);

        // Map the paginated requests to RequestWithNeedyDetails DTOs
        List<RequestWithNeedyDetails> requestWithNeedyDetailsList = requestsPage.getContent().stream()
                .map(requestMapper::mapRequestToRequestWithNeedyDetails)
                .collect(Collectors.toList());

        // Construct and return the paginated DTO
        return new PageDto<>(
                requestWithNeedyDetailsList,
                requestsPage.getTotalElements(),
                requestsPage.getTotalPages(),
                requestsPage.getNumber(),
                requestsPage.getSize(),
                requestsPage.isLast()
        );
    }

}
