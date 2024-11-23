package com.ntg.backend.service;

import com.ntg.backend.dto.ResponsePagination.PageDto;
import com.ntg.backend.dto.requestDto.ItemDto;
import com.ntg.backend.dto.requestDto.MessageDto;
import com.ntg.backend.dto.requestDto.requestStatusDto;
import com.ntg.backend.dto.responseDto.RequestWithItemDetails;
import com.ntg.backend.dto.requestDto.RequestDto;
import com.ntg.backend.dto.responseDto.RequestWithNeedyDetails;
import com.ntg.backend.entity.Request;

import java.util.List;

public interface RequestService {
    RequestWithItemDetails createRequest(RequestDto requestDto);
    RequestDto updateRequest(RequestDto requestDto, long id);
    RequestDto getRequestById(long id);
    void deleteRequest(long id);
    PageDto<RequestDto> getAllRequests(int pageNo, int pageSize);
    PageDto<RequestWithItemDetails> getRequestsByItemId(Long itemId, int pageNo, int pageSize);
    RequestWithItemDetails getRequestWithItemDetails(long requestId);
    MessageDto<requestStatusDto> changeRequestStatus(requestStatusDto requestStatusDto, long id);
    PageDto<RequestWithNeedyDetails> requestsWithNeedyDetails(long itemId, int pageNo, int pageSize);
}
