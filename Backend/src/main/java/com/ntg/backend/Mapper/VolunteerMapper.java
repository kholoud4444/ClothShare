package com.ntg.backend.Mapper;

import com.ntg.backend.dto.ResponsePagination.PageDto;
import com.ntg.backend.dto.requestDto.ItemDto;
import com.ntg.backend.dto.requestDto.VolunteerDto;
import com.ntg.backend.dto.responseDto.UserResponseDetails;
import com.ntg.backend.dto.responseDto.VolunteerWithItemsDetails;
import com.ntg.backend.entity.Item;
import com.ntg.backend.entity.User;
import com.ntg.backend.entity.Volunteer;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class VolunteerMapper {

    private final UserMapper userMapper; // Injecting the UserMapper
    private final ItemMapper itemMapper;

    public VolunteerMapper(UserMapper userMapper, ItemMapper itemMapper) {
        this.userMapper = userMapper;
        this.itemMapper = itemMapper;
    }

    public Volunteer mapToEntity(VolunteerDto volunteerDto) {
        Volunteer volunteer = new Volunteer();
        volunteer.setPassword(volunteerDto.getPassword());
        volunteer.setLocation(volunteerDto.getLocation());
        volunteer.setGender(volunteerDto.getGender());
        volunteer.setEmail(volunteerDto.getEmail());
        volunteer.setPhone(volunteerDto.getPhone());
        volunteer.setFirstName(volunteerDto.getFirstName());
        volunteer.setLastName(volunteerDto.getLastName());
        volunteer.setBirthDate(volunteerDto.getBirthDate());
        volunteer.setNationalId(volunteerDto.getNationalId());
        volunteer.setRole(volunteerDto.getRole());
        return volunteer;
    }

    public VolunteerWithItemsDetails toVolunteerWithItemsDetails(Volunteer volunteer) {
        VolunteerWithItemsDetails volunteerWithItemsDetails = new VolunteerWithItemsDetails();
        userMapper.mapToUserDto(volunteer, volunteerWithItemsDetails);

        List<ItemDto> itemsDto = volunteer.getItems().stream()
                .map(itemMapper::mapToItemDto)
                .collect(Collectors.toList());

        volunteerWithItemsDetails.setItems(itemsDto);
        return volunteerWithItemsDetails;
    }

    public List<ItemDto> toItemDtoList(Volunteer volunteer) {
        // If there are items related to the volunteer, map them to ItemDto
        return volunteer.getItems().stream()
                .map(itemMapper::mapToItemDto) // Map each Item to ItemDto
                .collect(Collectors.toList());
    }
public PageDto<ItemDto> itemDtoPageDto(Page<Item> itemPage) {
    List<ItemDto> itemDtos = itemPage.getContent().stream()
            .map(itemMapper::mapToItemDto)
            .collect(Collectors.toList());
    // Return a new PageDto with the mapped content and pagination details
    return new PageDto<>(
            itemDtos,
            itemPage.getTotalElements(),
            itemPage.getTotalPages(),
            itemPage.getNumber(),
            itemPage.getSize(),
            itemPage.isLast()
    );
}
    public PageDto<VolunteerWithItemsDetails> VolunteerPageToDto(Page<Volunteer> volunteerPage) {
        List<VolunteerWithItemsDetails> userResponseDetails = volunteerPage.getContent().stream()
                .map(this::toVolunteerWithItemsDetails)
                .collect(Collectors.toList());
        // Return a new PageDto with the mapped content and pagination details
        return new PageDto<>(
                userResponseDetails,
                volunteerPage.getTotalElements(),
                volunteerPage.getTotalPages(),
                volunteerPage.getNumber(),
                volunteerPage.getSize(),
                volunteerPage.isLast()
        );
    }
}
