package com.ntg.backend.service;

import com.ntg.backend.dto.ResponsePagination.PageDto;
import com.ntg.backend.dto.requestDto.ItemDto;

import com.ntg.backend.dto.requestDto.MessageDto;
import com.ntg.backend.dto.responseDto.RequestWithNeedyDetails;

import java.util.List;

public interface ItemService {
    ItemDto createItem(ItemDto itemDto);
    ItemDto updateItem(ItemDto itemDto, long id);
    PageDto<ItemDto> getAllItems(int pageNo, int pageSize);
    ItemDto getItemById(long id);
    void deleteItem(long id);
    MessageDto<ItemDto> changeItemStatus(ItemDto itemDto, long id);
}
