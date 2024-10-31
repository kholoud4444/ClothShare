package com.ntg.backend.Mapper;

import com.ntg.backend.dto.RequestDto;
import com.ntg.backend.entity.Request;
import org.springframework.stereotype.Component;

@Component
public class RequestMapper {
    public Request MaptoEntity(RequestDto requestDto) {
        Request request = new Request();
        request.setDate(requestDto.getDate());
        request.setReason(requestDto.getReason());
        request.setStatus(requestDto.getStatus());
        return request;

    }
}
