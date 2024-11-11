package com.ntg.backend.Mapper;

import com.ntg.backend.dto.requestDto.ItemDto;
import com.ntg.backend.dto.responseDto.ItemDetailsWithVolunteerName;
import com.ntg.backend.entity.Item;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ItemMapper {

    public Item mapToItemEntity(ItemDto itemDto) {
        Item item = new Item();
        item.setSize(itemDto.getSize());
        item.setImageUrl(itemDto.getImageUrl());
        item.setType(itemDto.getType());
        item.setGenderSuitability(itemDto.getGenderSuitability());
        item.setState(itemDto.getState());
        item.setStatus(itemDto.getStatus());
        item.setDescription(itemDto.getDescription());
        return item;
    }

    public ItemDto mapToItemDto(Item item) {
        ItemDto itemDto = new ItemDto();
        itemDto.setDescription(item.getDescription());
        itemDto.setType(item.getType());
        itemDto.setSize(item.getSize());
        itemDto.setGenderSuitability(item.getGenderSuitability());
        itemDto.setState(item.getState());
        itemDto.setImageUrl(item.getImageUrl());
        itemDto.setStatus(item.getStatus());
        itemDto.setVolunteerId(item.getVolunteer().getUserId());
        return itemDto;
    }
    public ItemDetailsWithVolunteerName mapToItemDetailsWithVolunteerName(Item item) {
        ItemDto itemDto = mapToItemDto(item);

        // Map fields to ItemDetailsWithVolunteerName
        ItemDetailsWithVolunteerName itemDetails = new ItemDetailsWithVolunteerName();
        itemDetails.setItem(itemDto);
        itemDetails.setVolunteerName(item.getVolunteer().getFirstName() + " " + item.getVolunteer().getLastName());
        itemDetails.setItemId(item.getItemId());

        return itemDetails;

    }

    public void updateEntityFromDto(ItemDto itemDto, Item item) {
        if (itemDto.getSize() != null) {
            item.setSize(itemDto.getSize());
        }
        if (itemDto.getImageUrl() != null) {
            item.setImageUrl(itemDto.getImageUrl());
        }
        if (itemDto.getType() != null) {
            item.setType(itemDto.getType());
        }
        if (itemDto.getGenderSuitability() != null) {
            item.setGenderSuitability(itemDto.getGenderSuitability());
        }
        if (itemDto.getState() != null) {
            item.setState(itemDto.getState());
        }
        if (itemDto.getStatus() != null) {
            item.setStatus(itemDto.getStatus());
        }
        if (itemDto.getDescription() != null) {
            item.setDescription(itemDto.getDescription());
        }
    }
}
