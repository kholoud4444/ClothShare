package com.ntg.backend.dto.responseDto;


import com.ntg.backend.dto.requestDto.RequestDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class NeedyWithRequestsList extends UserResponseDetails{
    private List<RequestDto> requests; // List of requests associated with the needy
}
