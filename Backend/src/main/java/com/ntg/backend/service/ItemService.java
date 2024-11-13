package com.ntg.backend.service;

import com.ntg.backend.dto.requestDto.ItemDto;

import com.ntg.backend.dto.responseDto.RequestWithItemDetails;
import com.ntg.backend.dto.responseDto.RequestWithNeedyDetails;
import com.ntg.backend.entity.Item;

import java.util.List;

public interface ItemService {
    ItemDto createItem(ItemDto itemDto);
    ItemDto updateItem(ItemDto itemDto, long id);
    List<ItemDto> getAllItems();
    ItemDto getItemById(long id);
    void deleteItem(long id);
    List<RequestWithNeedyDetails> requestWithNeedyDetails(long itemId);
}
