package com.ntg.backend.controller;

import com.ntg.backend.dto.ResponsePagination.PageDto;
import com.ntg.backend.dto.requestDto.ItemDto;
import com.ntg.backend.dto.requestDto.MessageDto;
import com.ntg.backend.dto.requestDto.RequestDto;
import com.ntg.backend.dto.requestDto.requestStatusDto;
import com.ntg.backend.dto.responseDto.VolunteerWithItemsDetails;
import com.ntg.backend.service.imp.RequestServiceImp;
import com.ntg.backend.service.imp.VolunteerServiceImp;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/volunteer")
@CrossOrigin("http://localhost:4200")
public class VolunteerController {

    private final VolunteerServiceImp volunteerServiceImp;
    private final RequestServiceImp requestServiceImp;

    public VolunteerController(VolunteerServiceImp volunteerServiceImp, RequestServiceImp requestServiceImp)
    {
        this.volunteerServiceImp = volunteerServiceImp;
        this.requestServiceImp = requestServiceImp;
    }

    // get specific Volunteer data with Items details
    @PreAuthorize("hasRole('volunteer')")
    @GetMapping("/itemDetails/{id}")
    public ResponseEntity<VolunteerWithItemsDetails>getVolunteerWithItemsDetails(@PathVariable("id") long id )
    {
        VolunteerWithItemsDetails volunteerWithItemsDetails = volunteerServiceImp.getVolunteerWithItemsDetails(id);
        return new ResponseEntity<>(volunteerWithItemsDetails,HttpStatus.OK);
    }

    // get all Volunteers data with Items details
//    @PreAuthorize("hasRole('ROLE_VOLUNTEER')")
    @GetMapping("/allItemsDetails")
    public ResponseEntity<PageDto<VolunteerWithItemsDetails>>getAllVolunteerWithItemsDetails  (
                @RequestParam (value = "pageNo",defaultValue = "0",required = false) int pageNo
                , @RequestParam (value = "pageSize",defaultValue = "5",required = false) int pageSize)
    {
        PageDto<VolunteerWithItemsDetails> volunteersWithItemsDetails = volunteerServiceImp
                .getAllVolunteersWithItems(pageNo,pageSize);
        return new ResponseEntity<>(volunteersWithItemsDetails,HttpStatus.OK);
    }

    //get all Items details for specific Volunteer
    @PreAuthorize(" hasRole('volunteer') or hasRole('admin')")
    @GetMapping("/allItemsDetailsById/{id}")
    public ResponseEntity<PageDto<ItemDto>> getAllItemsByVolunteerId(@PathVariable("id") long volunteerId,
                             @RequestParam (value = "pageNo",defaultValue = "0",required = false) int pageNo,
                             @RequestParam (value = "pageSize",defaultValue = "10",required = false) int pageSize)
    {
        PageDto<ItemDto> itemsPageByVolunteerId = volunteerServiceImp.getAllItemsByVolunteerId(volunteerId,pageNo,pageSize);
        return new ResponseEntity<>(itemsPageByVolunteerId,HttpStatus.OK);
    }
    @PreAuthorize("hasRole('admin')")

    @PutMapping("/changeRequestStatus/{requestId}")
        public  ResponseEntity<MessageDto<requestStatusDto>> changeItemRequest(@PathVariable("requestId") long requestId, @RequestBody requestStatusDto requestStatusDto)
        {
            MessageDto<requestStatusDto> updateRequestDto = requestServiceImp.changeRequestStatus(requestStatusDto, requestId);
            return new ResponseEntity<>(updateRequestDto,HttpStatus.OK);
        }

}
