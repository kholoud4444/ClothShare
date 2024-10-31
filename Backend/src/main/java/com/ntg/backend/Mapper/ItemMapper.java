package com.ntg.backend.Mapper;

import com.ntg.backend.dto.ItemDto;
import com.ntg.backend.entity.Item;
import org.springframework.stereotype.Component;

@Component
public class ItemMapper {
    public Item Mappertoentity(ItemDto itemDto) {
        Item item = new Item();
        item.setSize(itemDto.getSize());
        item.setImgUrl(itemDto.getImgUrl());
        item.setType(itemDto.getType());
        item.setGenderSuitability(itemDto.getGenderSuitability());
        item.setState(itemDto.getState());
        item.setStatus(itemDto.getStatus());
        item.setDescription(itemDto.getDescription());
        return  item;

    }
}
