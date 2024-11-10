package com.ntg.backend.Mapper;

import com.ntg.backend.dto.responseDto.NeedyInfo;
import com.ntg.backend.dto.responseDto.RequestWithItemDetails;
import com.ntg.backend.dto.requestDto.RequestDto;
import com.ntg.backend.dto.responseDto.RequestWithNeedyDetails;
import com.ntg.backend.entity.Item;
import com.ntg.backend.entity.Needy;
import com.ntg.backend.entity.Request;
import org.springframework.stereotype.Component;

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
        return requestDto;
    }

    public Request mapRequestToEntity(RequestDto requestDto) {
        if (requestDto == null) {
            return null;
        }
        Request request = new Request();
        request.setRequestId(requestDto.getRequestId());
        request.setDate(requestDto.getDate());
        request.setStatus(requestDto.getStatus());
        request.setReason(requestDto.getReason());

        Needy needy = new Needy();
        needy.setUserId(requestDto.getNeedyId());

        Item item = new Item();
        item.setItemId(requestDto.getItemId());

        request.setNeedy(needy);
        request.setItem(item);

        return request;
    }

    public RequestWithItemDetails mapToRequestWithItemDetails(Request request) {
        if (request == null) {
            return null;
        }
        RequestWithItemDetails requestWithItemDetails = new RequestWithItemDetails();

        requestWithItemDetails.setRequestStatus(request.getStatus().name()); // Mapping enum to string
        requestWithItemDetails.setReason(request.getReason());
        requestWithItemDetails.setDate(request.getDate());

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

        return requestWithNeedyDetails;
    }
}

