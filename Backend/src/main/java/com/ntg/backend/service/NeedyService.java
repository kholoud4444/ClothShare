package com.ntg.backend.service;

import com.ntg.backend.dto.requestDto.NeedyDto;
import com.ntg.backend.dto.requestDto.RequestDto;
import com.ntg.backend.dto.responseDto.NeedyRequestDetails;
import com.ntg.backend.dto.responseDto.RequestWithItemDetails;
import com.ntg.backend.entity.Needy;

import java.util.List;

public interface NeedyService {
    List<RequestDto> getAllRequestsByNeedyId(long needyId);
    List<RequestWithItemDetails> getAllRequestDetailsByNeedyId(long needyId);
}
