package com.ntg.backend.controller;

import com.ntg.backend.dto.requestDto.ItemDto;
import com.ntg.backend.dto.responseDto.VolunteerResponseDetails;
import com.ntg.backend.dto.requestDto.VolunteerDto;
import com.ntg.backend.dto.responseDto.VolunteerWithItemsDetails;
import com.ntg.backend.entity.Volunteer;
import com.ntg.backend.service.imp.VolunteerServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

@RequestMapping("/api/volunteer")
public class VolunteerController {

    private final VolunteerServiceImp volunteerServiceImp;

    public VolunteerController(VolunteerServiceImp volunteerServiceImp) {
        this.volunteerServiceImp = volunteerServiceImp;
    }


    @GetMapping("/details/{id}")
    public ResponseEntity<VolunteerWithItemsDetails>getVolunteerWithItemsDetails(@PathVariable("id") long id )
    {
        VolunteerWithItemsDetails volunteerWithItemsDetails = volunteerServiceImp.getVolunteerWithItemsDetails(id);
        return new ResponseEntity<>(volunteerWithItemsDetails,HttpStatus.OK);

    }
    @GetMapping("/all")
    public ResponseEntity< List<VolunteerWithItemsDetails>>getAllVolunteerWithItemsDetails()
    {
        List<VolunteerWithItemsDetails> volunteersWithItemsDetails = volunteerServiceImp.getAllVolunteersWithItems();
        return new ResponseEntity<>(volunteersWithItemsDetails,HttpStatus.OK);

    }
    @GetMapping("/all/items/{id}")
    public ResponseEntity<List<ItemDto>>getAllItemsByVolunteerId(@PathVariable("id") long volunteerId)
    {
        List<ItemDto> itemsByVolunteerId = volunteerServiceImp.getAllItemsByVolunteerId(volunteerId);
        return new ResponseEntity<>(itemsByVolunteerId,HttpStatus.OK);

    }



}
