package com.ntg.backend.service;

import com.ntg.backend.dto.requestDto.NeedyDto;
import com.ntg.backend.dto.responseDto.NeedyRequestDetails;
import com.ntg.backend.dto.responseDto.RequestWithItemDetails;
import com.ntg.backend.entity.Needy;

import java.util.List;

public interface NeedyService {
    Needy updateNeedy(NeedyDto needyDto,long id);
    List<Needy> getAllNeedy();
    Needy getNeedyById(long id);
    public List<RequestWithItemDetails> getAllRequestDetailsByNeedyId(long needyId);
}
