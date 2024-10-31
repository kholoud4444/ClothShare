package com.ntg.backend.service.imp;

import com.ntg.backend.Mapper.ItemMapper;
import com.ntg.backend.dto.ItemDto;
import com.ntg.backend.entity.Item;
import com.ntg.backend.entity.Volunteer;
import com.ntg.backend.exception.ResourceNotFoundException;
import com.ntg.backend.repository.ItemRepo;
import com.ntg.backend.repository.VolunteerRepo;
import com.ntg.backend.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemServiceImp implements ItemService {

    private final ItemRepo itemRepo;
    private final VolunteerRepo volunteerRepo;
    private final ItemMapper itemMapper;

    @Autowired
    public ItemServiceImp(ItemRepo itemRepo, VolunteerRepo volunteerRepo, ItemMapper itemMapper) {
        this.itemRepo = itemRepo;
        this.volunteerRepo = volunteerRepo;
        this.itemMapper = itemMapper;
    }

    @Override
    public Item createItem(ItemDto itemDto) {
        Volunteer volunteer = volunteerRepo.findById(itemDto.getVolunteerId())
                .orElseThrow(() -> new ResourceNotFoundException("Volunteer", "id", itemDto.getVolunteerId()));

        Item item = itemMapper.mapToEntity(itemDto);
        volunteer.getItems().add(item);
        item.setVolunteer(volunteer);
        return itemRepo.save(item);
    }

    @Override
    public Item updateItem(ItemDto itemDto, long id) {
        Item existingItem = itemRepo.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Item", "id", id));

        // Map updated fields from itemDto to existingItem
        itemMapper.updateEntityFromDto(itemDto, existingItem);

        return itemRepo.save(existingItem);
    }

    @Override
    public List<Item> getAllItems() {
        return itemRepo.findAll();
    }

    @Override
    public Item getItemById(long id) {
        return itemRepo.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Item", "id", id));
    }

    @Override
    public void deleteItem(long id) {
        Item item = itemRepo.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Item", "id", id));
        itemRepo.delete(item);
    }
}
