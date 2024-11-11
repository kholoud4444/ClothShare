package com.ntg.backend.service.imp;

import com.ntg.backend.Mapper.ItemMapper;
import com.ntg.backend.dto.responseDto.ItemDetailsWithVolunteerName;
import com.ntg.backend.entity.Item;
import com.ntg.backend.repository.AdminRepo;
import com.ntg.backend.repository.ItemRepo;
import com.ntg.backend.service.AdminService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdminServiceImp implements AdminService {

    private final ItemRepo itemRepo;
    private final ItemMapper itemMapper;

    public AdminServiceImp(ItemRepo itemRepo, ItemMapper itemMapper) {
        this.itemRepo = itemRepo;
        this.itemMapper = itemMapper;
    }

    @Override
    public List<ItemDetailsWithVolunteerName> GetAllItemDetailsWithVolunteerNameList() {
        List<Item> items = itemRepo.findAll();

            return items.stream()
                    .map(itemMapper::mapToItemDetailsWithVolunteerName) // Convert each Item to ItemDetailsWithVolunteerName
                    .collect(Collectors.toList()); // Collect them into a list
        }

    @Override
    public void ApproveItem(Long itemId) {
        Item item = itemRepo.findById(itemId)
                .orElseThrow(() -> new RuntimeException("Item not found"));
        item.setStatus(Item.ItemStatus.تم_الموافقه);
        itemRepo.save(item);

    }

    @Override
    public void RejectItem(Long itemId) {
        Item item = itemRepo.findById(itemId)
                .orElseThrow(() -> new RuntimeException("Item not found"));
        item.setStatus(Item.ItemStatus.معلق);
        itemRepo.save(item);
    }
}
