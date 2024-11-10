package com.ntg.backend.controller;

import com.ntg.backend.dto.requestDto.ItemDto;
import com.ntg.backend.dto.responseDto.VolunteerWithItemsDetails;
import com.ntg.backend.service.imp.VolunteerServiceImp;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    @GetMapping("/details/{id}")
    public ResponseEntity<VolunteerWithItemsDetails>getVolunteerWithItemsDetails(@PathVariable("id") long id )
    {
        VolunteerWithItemsDetails volunteerWithItemsDetails = volunteerServiceImp.getVolunteerWithItemsDetails(id);
        return new ResponseEntity<>(volunteerWithItemsDetails,HttpStatus.OK);
    }

    // get all Volunteers data with Items details
    @GetMapping("/all")
    public ResponseEntity< List<VolunteerWithItemsDetails>>getAllVolunteerWithItemsDetails()
    {
        List<VolunteerWithItemsDetails> volunteersWithItemsDetails = volunteerServiceImp.getAllVolunteersWithItems();
        return new ResponseEntity<>(volunteersWithItemsDetails,HttpStatus.OK);
    }

    //get all Items details for specific Volunteer
    @GetMapping("/all/items/{id}")
    public ResponseEntity<List<ItemDto>>getAllItemsByVolunteerId(@PathVariable("id") long volunteerId)
    {
        List<ItemDto> itemsByVolunteerId = volunteerServiceImp.getAllItemsByVolunteerId(volunteerId);
        return new ResponseEntity<>(itemsByVolunteerId,HttpStatus.OK);
    }

}
