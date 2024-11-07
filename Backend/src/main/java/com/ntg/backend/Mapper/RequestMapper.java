package com.ntg.backend.Mapper;

import com.ntg.backend.dto.responseDto.RequestWithItemDetails;
import com.ntg.backend.dto.requestDto.RequestDto;
import com.ntg.backend.entity.Item;
import com.ntg.backend.entity.Needy;
import com.ntg.backend.entity.Request;
import org.springframework.stereotype.Component;

@Component
public class RequestMapper {

    public static RequestDto toDto(Request request) {
        if (request == null) {
            return null;
        }

        RequestDto requestDto = new RequestDto();
        requestDto.setRequestId(request.getRequestId());
        requestDto.setDate(request.getDate());
        requestDto.setStatus(request.getStatus());
        requestDto.setReason(request.getReason());
        return requestDto;
    }

    public Request mapToEntity(RequestDto requestDto) {
        if (requestDto == null) {
            return null;
        }

        // Create a new Request entity
        Request request = new Request();

        // Map properties from RequestDto to Request entity
        request.setRequestId(requestDto.getRequestId());  // If the requestId is set in the DTO
        request.setDate(requestDto.getDate());
        request.setStatus(requestDto.getStatus());
        request.setReason(requestDto.getReason());

        // Retrieve Needy and Item entities based on their IDs
        Needy needy = new Needy();  // Assuming the Needy entity is just an object with an ID
        needy.setUserId(requestDto.getNeedyId());  // Set Needy ID to the object

        Item item = new Item();  // Same with Item, assuming you have an Item object
        item.setItemId(requestDto.getItemId());  // Set Item ID to the object

        // Set the related entities in the Request
        request.setNeedy(needy);
        request.setItem(item);

        return request;
    }
    // Method to map Request entity with Item details to RequestWithItemDetails DTO
    public RequestWithItemDetails mapToRequestWithItemDetails(Request request) {
        if (request == null) {
            return null; // Return null if the request is null
        }

        // Create a new instance of the DTO
        RequestWithItemDetails dto = new RequestWithItemDetails();

        // Map Request details
        dto.setRequestStatus(request.getStatus().name()); // Mapping enum to string
        dto.setReason(request.getReason());
        dto.setDate(java.sql.Date.valueOf(request.getDate())); // Convert LocalDate to Date

        // Get Item from the Request and map the Item details
        Item item = request.getItem();
        if (item != null) {
            dto.setType(item.getType().name()); // Map enum to string
            dto.setSize(item.getSize().name()); // Map enum to string
            dto.setState(item.getState().name()); // Map enum to string
            dto.setGenderSuitability(item.getGenderSuitability().name()); // Map enum to string
            dto.setImageUrl(item.getImageUrl()); // Map Image URL
            dto.setDescription(item.getDescription()); // Map Description
            dto.setStatus(item.getStatus().name()); // Map ItemStatus enum to string
        }

        return dto;
    }
}

