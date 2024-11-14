package com.ntg.backend.service;

import com.ntg.backend.dto.ResponsePagination.PageDto;
import com.ntg.backend.dto.responseDto.ItemDetailsWithVolunteerName;
import com.ntg.backend.entity.Item;

import java.util.List;

public interface AdminService {
    PageDto<ItemDetailsWithVolunteerName> GetAllItemDetailsWithVolunteerNameList(int PageNo,int PageSize);
}
