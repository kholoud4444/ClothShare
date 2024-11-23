package com.ntg.backend.dto.requestDto;

import com.ntg.backend.entity.Request;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class requestStatusDto {
    Request.RequestStatus status;
}
