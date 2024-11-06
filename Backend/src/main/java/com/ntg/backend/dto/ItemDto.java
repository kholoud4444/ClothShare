package com.ntg.backend.dto;


import com.ntg.backend.entity.Item;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class ItemDto {
    private Item.ClothingType type;
    private Item.ClothingSize size;
    private Item.ItemState state;
    private Item.GenderSuitability genderSuitability;
    private String imageUrl;
    private String description;
    private Item.ItemStatus status;
    private Long volunteerId; // Optional: include volunteer ID if needed

}
