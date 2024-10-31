package com.ntg.backend.Mapper;

import com.ntg.backend.dto.RequestDto;
import com.ntg.backend.entity.Request;
import org.springframework.stereotype.Component;

@Component
public class RequestMapper {

    public Request mapToEntity(RequestDto requestDto) {
        Request request = new Request();
        request.setDate(requestDto.getDate());
        request.setReason(requestDto.getReason());
        request.setStatus(requestDto.getStatus());
        return request;
    }

    public void updateEntityFromDto(RequestDto requestDto, Request request) {
        if (requestDto.getDate() != null) {
            request.setDate(requestDto.getDate());
        }
        if (requestDto.getReason() != null) {
            request.setReason(requestDto.getReason());
        }
        if (requestDto.getStatus() != null) {
            request.setStatus(requestDto.getStatus());
        }
    }
}
