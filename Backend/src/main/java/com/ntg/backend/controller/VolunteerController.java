package com.ntg.backend.controller;

import com.ntg.backend.dto.VolunteerDto;
import com.ntg.backend.service.imp.VolunteerServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/volunteer")
public class VolunteerController {
    @Autowired
    private VolunteerServiceImp volunteerServiceImp;
    @PostMapping
    public ResponseEntity<VolunteerDto> addVolunteer(@RequestBody VolunteerDto volunteerDto) {
        VolunteerDto savedVolunteerDto = volunteerServiceImp.addVolunteer(volunteerDto);
        return new ResponseEntity<>(savedVolunteerDto, HttpStatus.OK);
    }
    @GetMapping("{id}")
    public ResponseEntity<VolunteerDto>getVolunteerById(@PathVariable("id") long id )
    {
        VolunteerDto volunteerDto = volunteerServiceImp.getVolunteerById(id);
        return new ResponseEntity<>(volunteerDto,HttpStatus.OK);
        
    }
    
    @GetMapping
    public ResponseEntity<List<VolunteerDto>>getAllVolunteer()
    {
        List<VolunteerDto> volunteerDTOs = volunteerServiceImp.getAllVolunteers() ;
        return new ResponseEntity<>(volunteerDTOs,HttpStatus.OK);

    }
    
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteVolunteerById(@PathVariable("id") Long id)
    {
        volunteerServiceImp.deleteVolunteerById(id);
        return new ResponseEntity<>("Deleted",HttpStatus.OK);
    }
    @PutMapping("{id}")
    public ResponseEntity<VolunteerDto> VolunteerDto(@RequestBody VolunteerDto volunteerDto,@PathVariable("id") long id)
    {
        VolunteerDto updatedvolunteerDto = volunteerServiceImp.updateVolunteer(volunteerDto,id);
        return new ResponseEntity<>(updatedvolunteerDto,HttpStatus.OK);
    }
   

  


}
