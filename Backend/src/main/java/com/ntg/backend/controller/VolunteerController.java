package com.ntg.backend.controller;

import com.ntg.backend.dto.ResponsePagination.PageDto;
import com.ntg.backend.dto.requestDto.ItemDto;
import com.ntg.backend.dto.responseDto.VolunteerWithItemsDetails;
import com.ntg.backend.service.imp.VolunteerServiceImp;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/volunteer")
public class VolunteerController {

    private final VolunteerServiceImp volunteerServiceImp;

    public VolunteerController(VolunteerServiceImp volunteerServiceImp)
    {
        this.volunteerServiceImp = volunteerServiceImp;
    }

    // get specific Volunteer data with Items details
    @PreAuthorize("hasRole('ROLE_VOLUNTEER')")
    @GetMapping("/details/{id}")
    public ResponseEntity<VolunteerWithItemsDetails>getVolunteerWithItemsDetails(@PathVariable("id") long id )
    {
        VolunteerWithItemsDetails volunteerWithItemsDetails = volunteerServiceImp.getVolunteerWithItemsDetails(id);
        return new ResponseEntity<>(volunteerWithItemsDetails,HttpStatus.OK);
    }

    // get all Volunteers data with Items details
    @PreAuthorize("hasRole('ROLE_VOLUNTEER')")
    @GetMapping("/all")
    public ResponseEntity<PageDto<VolunteerWithItemsDetails>>getAllVolunteerWithItemsDetails  (
            @RequestParam (value = "pageNo",defaultValue = "0",required = false) int pageNo
            , @RequestParam (value = "pageSize",defaultValue = "10",required = false) int pageSize)
    {
        PageDto<VolunteerWithItemsDetails> volunteersWithItemsDetails = volunteerServiceImp
                .getAllVolunteersWithItems(pageNo,pageSize);
        return new ResponseEntity<>(volunteersWithItemsDetails,HttpStatus.OK);
    }

    //get all Items details for specific Volunteer
    @PreAuthorize("hasRole('ROLE_VOLUNTEER')")
    @GetMapping("/all/items/{id}")
    public ResponseEntity<PageDto<ItemDto>> getAllItemsByVolunteerId(@PathVariable("id") long volunteerId,
                             @RequestParam (value = "pageNo",defaultValue = "0",required = false) int pageNo,
                             @RequestParam (value = "pageSize",defaultValue = "10",required = false) int pageSize)
    {
        PageDto<ItemDto> itemsPageByVolunteerId = volunteerServiceImp.getAllItemsByVolunteerId(volunteerId,pageNo,pageSize);
        return new ResponseEntity<>(itemsPageByVolunteerId,HttpStatus.OK);
    }

}
