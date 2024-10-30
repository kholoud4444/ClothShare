package com.ntg.backend.service;

import com.ntg.backend.dto.RequestDto;

import java.util.List;

public interface RequestService {
    RequestDto createRequest(RequestDto requestDto);
    RequestDto updateRequest(RequestDto requestDto,long id);
    List<RequestDto> getAllRequests();
    RequestDto getRequestById(long id);
    void deleteRequest(long id);
}
