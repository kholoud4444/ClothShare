package com.ntg.backend.service;

import com.ntg.backend.dto.responseDto.RequestWithItemDetails;
import com.ntg.backend.dto.requestDto.RequestDto;
import com.ntg.backend.entity.Request;

import java.util.List;

public interface RequestService {
    Request createRequest(RequestDto requestDto);
    Request updateRequest(RequestDto requestDto, long id);
    List<Request> getAllRequests();
    Request getRequestById(long id);
    void deleteRequest(long id);
    List<Request> getAllRequestsByNeedyId(long NEEDED_ID);
    RequestWithItemDetails getRequestWithItemDetails(long requestId);
}
