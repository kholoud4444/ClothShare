package com.ntg.backend.Mapper;

import com.ntg.backend.dto.ResponsePagination.PageDto;
import com.ntg.backend.dto.responseDto.NeedyInfo;
import com.ntg.backend.dto.responseDto.RequestWithItemDetails;
import com.ntg.backend.dto.requestDto.RequestDto;
import com.ntg.backend.dto.responseDto.RequestWithNeedyDetails;
import com.ntg.backend.entity.Item;
import com.ntg.backend.entity.Needy;
import com.ntg.backend.entity.Request;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class RequestMapper {
    private final ItemMapper itemMapper;
    private final NeedyMapper needyMapper;

    public RequestMapper(ItemMapper itemMapper, NeedyMapper needyMapper) {
        this.itemMapper = itemMapper;
        this.needyMapper = needyMapper;
    }

    public RequestDto mapToRequestDto(Request request) {
        if (request == null) {
            return null;
        }
        RequestDto requestDto = new RequestDto();
        requestDto.setRequestId(request.getRequestId());
        requestDto.setDate(request.getDate());
        requestDto.setStatus(request.getStatus());
        requestDto.setReason(request.getReason());
        requestDto.setNeedyId(request.getNeedy().getUserId());
        requestDto.setItemId(request.getItem().getItemId());
        return requestDto;
    }

    public Request mapToRequestEntity(RequestDto requestDto) {
        if (requestDto == null) {
            return null;
        }
        Request request = new Request();
        request.setRequestId(requestDto.getRequestId());
        request.setDate(requestDto.getDate());
        request.setStatus(requestDto.getStatus());
        request.setReason(requestDto.getReason());


        return request;
    }

    public PageDto<RequestDto> requestDtoPageDto(Page<Request> requestPage) {
        List<RequestDto> requestDtos = requestPage.getContent().stream()
                .map(this::mapToRequestDto)
                .collect(Collectors.toList());
        // Return a new PageDto with the mapped content and pagination details
        return new PageDto<>(
                requestDtos,
                requestPage.getTotalElements(),
                requestPage.getTotalPages(),
                requestPage.getNumber(),
                requestPage.getSize(),
                requestPage.isLast()
        );

    }

    public RequestWithItemDetails mapToRequestWithItemDetails(Request request) {
        if (request == null) {
            return null;
        }
        RequestWithItemDetails requestWithItemDetails = new RequestWithItemDetails();

        requestWithItemDetails.setRequestStatus(request.getStatus().name()); // Mapping enum to string
        requestWithItemDetails.setReason(request.getReason());
        requestWithItemDetails.setDate(request.getDate());
        requestWithItemDetails.setRequestId(request.getRequestId());
        requestWithItemDetails.setNeedyId(request.getNeedy().getUserId());

        requestWithItemDetails.setItemData(itemMapper.mapToItemDto(request.getItem()));
        return requestWithItemDetails;
    }

    public RequestWithNeedyDetails mapRequestToRequestWithNeedyDetails(Request request) {
        // Fetch Needy User details
        Needy needyUser = request.getNeedy();

        // Map Needy entity to NeedyInfo DTO
        NeedyInfo needyInfo = needyMapper.mapNeedyToNeedyInfo(needyUser);

        // Create and populate RequestWithNeedyDetails DTO
        RequestWithNeedyDetails requestWithNeedyDetails = new RequestWithNeedyDetails();
        requestWithNeedyDetails.setRequestStatus(String.valueOf(request.getStatus()));
        requestWithNeedyDetails.setReason(request.getReason());
        requestWithNeedyDetails.setDate(request.getDate());
        requestWithNeedyDetails.setNeedyInfo(needyInfo);
        requestWithNeedyDetails.setRequestId(request.getRequestId());

        return requestWithNeedyDetails;
    }

    public void updateEntityFromDto(RequestDto requestDto, Request request) {
        if (requestDto.getStatus() != null) {
            request.setStatus(requestDto.getStatus());
        }
        if (requestDto.getReason() != null) {
            request.setReason(requestDto.getReason());
        }
    }
}

