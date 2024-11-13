package com.ntg.backend.service;

import com.ntg.backend.dto.ResponsePagination.PageDto;
import com.ntg.backend.dto.requestDto.NeedyDto;
import com.ntg.backend.dto.requestDto.RequestDto;
import com.ntg.backend.dto.responseDto.NeedyRequestDetails;
import com.ntg.backend.dto.responseDto.RequestWithItemDetails;
import com.ntg.backend.entity.Needy;

import java.util.List;

public interface NeedyService {
    PageDto<RequestWithItemDetails> getAllRequestDetailsByNeedyId(long needyId, int pageNo, int pageSize);
    PageDto<RequestWithItemDetails> getAllRequestDetailsForAllNeedies(int pageNo, int pageSize);
}
