package com.ntg.backend.service;

import com.ntg.backend.dto.ItemDto;

import java.util.List;

public interface ItemService {
    ItemDto createItem(ItemDto itemDto);
    ItemDto updateItem(ItemDto itemDto,long id);
    List<ItemDto> getAllItem();
    ItemDto getItemById(long id);
    void deleteItem(long id);
}
