package com.ntg.backend.service;

import com.ntg.backend.dto.responseDto.ItemDetailsWithVolunteerName;

import java.util.List;

public interface AdminService {
    List<ItemDetailsWithVolunteerName> GetAllItemDetailsWithVolunteerNameList();
    void ApproveItem(Long ItemId);
    void RejectItem(Long ItemId);


}
