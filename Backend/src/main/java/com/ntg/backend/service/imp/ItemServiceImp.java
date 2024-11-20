package com.ntg.backend.service.imp;

import com.ntg.backend.Mapper.ItemMapper;
import com.ntg.backend.dto.ResponsePagination.PageDto;
import com.ntg.backend.dto.requestDto.ItemDto;
import com.ntg.backend.dto.requestDto.MessageDto;
import com.ntg.backend.entity.Item;
import com.ntg.backend.exception.ResourceNotFoundException;
import com.ntg.backend.repository.ItemRepo;
import com.ntg.backend.repository.VolunteerRepo;
import com.ntg.backend.service.ItemService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ItemServiceImp implements ItemService {

    private final ItemRepo itemRepo;
    private final VolunteerRepo volunteerRepo;
    private final ItemMapper itemMapper;

    public ItemServiceImp(ItemRepo itemRepo, VolunteerRepo volunteerRepo, ItemMapper itemMapper) {
        this.itemRepo = itemRepo;
        this.volunteerRepo = volunteerRepo;
        this.itemMapper = itemMapper;
    }

    @Override
    public ItemDto createItem(ItemDto itemDto) {
        Item item = itemMapper.mapToItemEntity(itemDto);
        item.setVolunteer(volunteerRepo.findById(itemDto.getVolunteerId())
                .orElseThrow(() -> new ResourceNotFoundException("Volunteer not found with id : " + itemDto.getVolunteerId())));
        item = itemRepo.save(item);
        return itemMapper.mapToItemDto(item);
    }

    @Override
    public ItemDto updateItem(ItemDto itemDto, long id) {
        Item existingItem = itemRepo.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Item", "id", id));
        itemMapper.updateEntityFromDto(itemDto, existingItem);
        return itemMapper.mapToItemDto(itemRepo.save(existingItem));
    }

    @Override
    public PageDto<ItemDto> getAllItems(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<Item> items = itemRepo.findAll(pageable);
        return itemMapper.itemDtoPageDto(items);
    }

    @Override
    public ItemDto getItemById(long id) {
        Item item = itemRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Item", "id", id));
        return itemMapper.mapToItemDto(item);
    }

    @Override
    public void deleteItem(long id) {
        itemRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Item", "id", id));
        itemRepo.deleteById(id);
    }

    @Override
    public MessageDto<ItemDto> changeItemStatus(ItemDto itemDto, long id) {
        Item existingItem = itemRepo.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Item", "id", id));

        // Check if the status from the DTO is different from the existing status
        if (itemDto.getStatus() != existingItem.getStatus()) {
            // Update the status if different and save to database
            existingItem.setStatus(itemDto.getStatus());
            itemRepo.save(existingItem);
            ItemDto currentItem= itemMapper.mapToItemDto(existingItem);// Save the updated item status
            String message = "Item status updated successfully to " + existingItem.getStatus();
            return new MessageDto<>(message, currentItem);
        } else {
            ItemDto currentItem= itemMapper.mapToItemDto(existingItem);
            String message = "Item status is already " + existingItem.getStatus() + "; no changes made.";
            return new MessageDto<>(message, currentItem);
        }
    }
}
