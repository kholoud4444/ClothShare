package com.ntg.backend.service;

import com.ntg.backend.dto.requestDto.ItemDto;
import com.ntg.backend.entity.Item;

import java.util.List;

public interface ItemService {
    Item createItem(ItemDto itemDto);
    Item updateItem(ItemDto itemDto, long id);
    List<Item> getAllItems();
    Item getItemById(long id);
    void deleteItem(long id);
}
