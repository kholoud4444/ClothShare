package com.ntg.backend.service.imp;

import com.ntg.backend.dto.ItemDto;
import com.ntg.backend.entity.Item;
import com.ntg.backend.repository.ItemRepo;
import com.ntg.backend.service.ItemService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ItemServiceImp implements ItemService {

    @Autowired
    private ItemRepo itemRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ItemDto createItem(ItemDto itemDto) {
        Item item = modelMapper.map(itemDto, Item.class);
        Item savedItem = itemRepo.save(item);
        return modelMapper.map(savedItem, ItemDto.class);
    }

    @Override
    public ItemDto updateItem(ItemDto itemDto,long id) {
        Item item = itemRepo.findById(id).get();
        item.setType(itemDto.getType());
        item.setSize(itemDto.getSize());
        item.setState(itemDto.getState());
        item.setGenderSuitability(itemDto.getGenderSuitability());
        item.setImgUrl(itemDto.getImgUrl());
        item.setDescription(itemDto.getDescription());
        item.setStatus(itemDto.getStatus());
        Item savedItem = itemRepo.save(item);
        return modelMapper.map(savedItem, ItemDto.class);
    }

    @Override
    public List<ItemDto> getAllItems() {
        List<Item> items = itemRepo.findAll();
        return items.stream().map(item -> modelMapper.map(item, ItemDto.class)).collect(Collectors.toList());
    }

    @Override
    public ItemDto getItemById(long id) {
        Item needy = itemRepo.findById(id).get();
        return modelMapper.map(needy, ItemDto.class);
    }

    @Override
    public void deleteItem(long id) {
        Item needy =  itemRepo.findById(id).get();
        itemRepo.delete(needy);
    }
}
