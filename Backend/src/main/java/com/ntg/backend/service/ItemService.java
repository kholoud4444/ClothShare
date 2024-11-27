package com.ntg.backend.service;

import com.ntg.backend.dto.ResponsePagination.PageDto;
import com.ntg.backend.dto.requestDto.ItemDto;

import com.ntg.backend.dto.requestDto.MessageDto;
import com.ntg.backend.dto.responseDto.RequestWithNeedyDetails;
import com.ntg.backend.entity.Item;

import java.util.List;
import java.util.Optional;

public interface ItemService {
    ItemDto createItem(ItemDto itemDto);
    ItemDto updateItem(ItemDto itemDto, long id);
    PageDto<ItemDto> getAllItems(int pageNo, int pageSize);
    ItemDto getItemById(long id);
    void deleteItem(long id);
    MessageDto<ItemDto> changeItemStatus(ItemDto itemDto, long id);
    public PageDto<ItemDto> getAllItemsByFilters(
            Optional<Item.ClothingType> type,
            Optional<Item.ClothingSize> size,
            Optional<Item.ItemState> state,
            Optional<Item.GenderSuitability> genderSuitability,
            Optional<Item.ItemStatus> status, // Added status filter
            int pageNo,
            int pageSize
    );
}
