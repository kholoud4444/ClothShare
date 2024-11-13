package com.ntg.backend.service;

import com.ntg.backend.dto.ResponsePagination.PageDto;
import com.ntg.backend.dto.responseDto.ItemDetailsWithVolunteerName;

import java.util.List;

public interface AdminService {
    public PageDto<ItemDetailsWithVolunteerName> GetAllItemDetailsWithVolunteerNameList(int PageNo,int PageSize);
    void ApproveItem(Long ItemId);
    void RejectItem(Long ItemId);


}
