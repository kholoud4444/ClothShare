package com.ntg.backend.controller;

import com.ntg.backend.dto.VolunteerDto;
import com.ntg.backend.entity.Volunteer;
import com.ntg.backend.service.imp.VolunteerServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/Volunteer")
public class VolunteerController {
    @Autowired
    private VolunteerServiceImp volunteerserviceimp;
    @PostMapping
    public ResponseEntity<VolunteerDto> addVolunteer(@RequestBody VolunteerDto volunteerDto) {
        VolunteerDto savedVolunterDto = volunteerserviceimp.addVolunteer(volunteerDto);
        return new ResponseEntity<>(savedVolunterDto, HttpStatus.OK);
    }
    @GetMapping("{id}")
    public ResponseEntity<VolunteerDto>getVolunteerbyid(@PathVariable("id") long id )
    {
        VolunteerDto volunteerDto = volunteerserviceimp.getVolunteerById(id);
        return new ResponseEntity<>(volunteerDto,HttpStatus.OK);
        
    }
    
    @GetMapping
    public ResponseEntity<List<VolunteerDto>>getAllVolunteer()
    {
        List<VolunteerDto> volunteerDtos = volunteerserviceimp.getAllVolunteers() ;
        return new ResponseEntity<>(volunteerDtos,HttpStatus.OK);

    }
    
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteVolunteerById(@PathVariable("id") Long id)
    {
        volunteerserviceimp.deleteVolunteerById(id);
        return new ResponseEntity<>("Deleted",HttpStatus.OK);
    }
    @PutMapping("{id}")
    public ResponseEntity<VolunteerDto> VolunteerDto(@RequestBody VolunteerDto volunteerDto,@PathVariable("id") long id)
    {
        VolunteerDto updatedvolunteerDto = volunteerserviceimp.updateVolunteer(volunteerDto,id);
        return new ResponseEntity<>(updatedvolunteerDto,HttpStatus.OK);
    }
   

  


}
