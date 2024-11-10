package com.ntg.backend.service.imp;

import com.ntg.backend.Mapper.ItemMapper;
import com.ntg.backend.Mapper.NeedyMapper;
import com.ntg.backend.Mapper.RequestMapper;
import com.ntg.backend.dto.requestDto.ItemDto;
import com.ntg.backend.dto.responseDto.NeedyInfo;
import com.ntg.backend.dto.responseDto.RequestWithItemDetails;
import com.ntg.backend.dto.responseDto.RequestWithNeedyDetails;
import com.ntg.backend.dto.responseDto.UserResponseDetails;
import com.ntg.backend.entity.Item;
import com.ntg.backend.entity.Needy;
import com.ntg.backend.entity.Request;
import com.ntg.backend.entity.Volunteer;
import com.ntg.backend.exception.ResourceNotFoundException;
import com.ntg.backend.repository.ItemRepo;
import com.ntg.backend.repository.RequestRepo;
import com.ntg.backend.repository.VolunteerRepo;
import com.ntg.backend.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ItemServiceImp implements ItemService {

    private final ItemRepo itemRepo;
    private final RequestRepo requestRepo;
    private final VolunteerRepo volunteerRepo;
    private final ItemMapper itemMapper;
    private final NeedyMapper needyMapper;
    private final RequestMapper requestMapper;



    public ItemServiceImp(ItemRepo itemRepo, RequestRepo requestRepo, VolunteerRepo volunteerRepo, ItemMapper itemMapper, NeedyMapper needyMapper, RequestMapper requestMapper) {
        this.itemRepo = itemRepo;
        this.requestRepo = requestRepo;
        this.volunteerRepo = volunteerRepo;
        this.itemMapper = itemMapper;
        this.needyMapper = needyMapper;
        this.requestMapper = requestMapper;
    }

    @Override
    public Item createItem(ItemDto itemDto) {
        Volunteer volunteer = volunteerRepo.findById(itemDto.getVolunteerId())
                .orElseThrow(() -> new ResourceNotFoundException("Volunteer", "id", itemDto.getVolunteerId()));

        Item item = itemMapper.mapToItemEntity(itemDto);
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

    @Override
  public List<RequestWithNeedyDetails> requestWithNeedyDetails(long itemId) {
        // Step 1: Fetch the Item (this will also fetch its associated requests)
        Item item = itemRepo.findById(itemId)
                .orElseThrow(() -> new ResourceNotFoundException("Item", "id", itemId));

        // Step 2: Fetch all associated Requests for the Item
        List<Request> requests = requestRepo.findByItem(item);// Assuming this returns a List<Request>
        if (requests.isEmpty()) {
            throw new ResourceNotFoundException("Request", "itemId", itemId);  // Or handle in another way if needed
        }

        // Step 3: Process each Request and map it to a DTO
        List<RequestWithNeedyDetails> requestWithNeedyDetailsList = new ArrayList<>();
        for (Request request : requests) {
            // Fetch Needy User details
          RequestWithNeedyDetails  requestWithNeedyDetails = requestMapper.mapRequestToRequestWithNeedyDetails(request);


            // Add the DTO to the list
            requestWithNeedyDetailsList.add(requestWithNeedyDetails);
        }

        // Step 4: Return the populated list of DTOs
        return requestWithNeedyDetailsList;
    }


}
