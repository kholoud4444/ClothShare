package com.ntg.backend.service;

import com.ntg.backend.dto.RequestDto;
import com.ntg.backend.entity.Request;

import java.util.List;

public interface RequestService {
    Request createRequest(RequestDto requestDto,Long id_needy,Long id_items);
//    Request updateRequest(RequestDto requestDto, long id);
    List<Request> getAllRequests();
    Request getRequestById(long id);
    void deleteRequest(long id);
}
