package com.ntg.backend.service.imp;

import com.ntg.backend.Mapper.ItemMapper;
import com.ntg.backend.Mapper.RequestMapper;
import com.ntg.backend.dto.ResponsePagination.PageDto;
import com.ntg.backend.dto.requestDto.ItemDto;
import com.ntg.backend.dto.responseDto.RequestWithNeedyDetails;
import com.ntg.backend.entity.Item;
import com.ntg.backend.exception.ResourceNotFoundException;
import com.ntg.backend.repository.ItemRepo;
import com.ntg.backend.repository.RequestRepo;
import com.ntg.backend.repository.VolunteerRepo;
import com.ntg.backend.service.ItemService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ItemServiceImp implements ItemService {

    private final ItemRepo itemRepo;
    private final VolunteerRepo volunteerRepo;
    private final ItemMapper itemMapper;
    private final RequestRepo requestRepo;
    private final RequestMapper requestMapper;

    public ItemServiceImp(ItemRepo itemRepo, VolunteerRepo volunteerRepo, ItemMapper itemMapper, RequestRepo requestRepo, RequestMapper requestMapper) {
        this.itemRepo = itemRepo;
        this.volunteerRepo = volunteerRepo;
        this.itemMapper = itemMapper;
        this.requestRepo = requestRepo;
        this.requestMapper = requestMapper;
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
    public List<RequestWithNeedyDetails> requestWithNeedyDetails(long itemId) {
        Item item = itemRepo.findById(itemId).orElseThrow(() -> new ResourceNotFoundException("Item", "id", itemId));
        return requestRepo.findByItem(item).stream()
                .map(requestMapper::mapRequestToRequestWithNeedyDetails)
                .collect(Collectors.toList());
    }
}
