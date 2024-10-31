package com.ntg.backend.service.imp;

import com.ntg.backend.Mapper.ItemMapper;
import com.ntg.backend.dto.ItemDto;
import com.ntg.backend.entity.Item;
import com.ntg.backend.entity.Volunteer;
import com.ntg.backend.exception.ResourceNotFoundException;
import com.ntg.backend.repository.ItemRepo;
import com.ntg.backend.repository.VolunteerRepo;
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
    private VolunteerRepo volunteerRepository;


    @Autowired
    private ItemMapper itemMapper;

    @Override
    public Item createItem(ItemDto itemDto) {
        Volunteer volunteer = volunteerRepository.findById(itemDto.getVolunteerId())
                .orElseThrow(() -> new RuntimeException("Volunteer not found"));

        Item item = itemMapper.Mappertoentity(itemDto);

        volunteer.getItems().add(item);
        item.setVolunteer(volunteer);

        itemRepo.save(item);

        // Map saved item back to ItemDto and return it
        return item;
    }


    @Override
    public Item updateItem(ItemDto itemDto,long id) {
        Item item = itemRepo.findById(id).orElseThrow(()
                -> new ResourceNotFoundException("User", "id", id));
       Item newItem = itemMapper.Mappertoentity(itemDto);
        Item savedItem = itemRepo.save(newItem);
        return savedItem;
    }

    @Override
    public List<Item> getAllItems() {
        List<Item> items = itemRepo.findAll();
        return items;
    }

    @Override
    public Item getItemById(long id) {
        Item needy = itemRepo.findById(id).orElseThrow(()
                -> new ResourceNotFoundException("User", "id", id));
        return needy;
    }

    @Override
    public void deleteItem(long id) {
        Item needy =  itemRepo.findById(id).orElseThrow(()
                -> new ResourceNotFoundException("User", "id", id));
        itemRepo.delete(needy);
    }
}
