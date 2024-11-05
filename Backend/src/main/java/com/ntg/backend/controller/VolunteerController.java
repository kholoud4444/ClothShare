package com.ntg.backend.controller;

import com.ntg.backend.dto.VolunteerDto;
import com.ntg.backend.entity.Volunteer;
import com.ntg.backend.service.imp.VolunteerServiceImp;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

@RequestMapping("/api/volunteer")
public class VolunteerController {
    @Autowired
    private VolunteerServiceImp volunteerServiceImp;
    //    @PostMapping
//    public ResponseEntity<Volunteer> addVolunteer(@RequestBody VolunteerDto volunteerDto) {
//        Volunteer savedVolunteerDto = volunteerServiceImp.addVolunteer(volunteerDto);
//        return new ResponseEntity<>(savedVolunteerDto, HttpStatus.OK);
//    }
    @PreAuthorize("hasRole('volunteer')")
    @GetMapping("{id}")
    public ResponseEntity<Volunteer>getVolunteerById(@PathVariable("id") long id )
    {
        Volunteer volunteerDto = volunteerServiceImp.getVolunteerById(id);
        return new ResponseEntity<>(volunteerDto,HttpStatus.OK);

    }

    @PreAuthorize("hasRole('ROLE_VOLUNTEER')")
    @GetMapping
    public ResponseEntity<List<Volunteer>>getAllVolunteer()
    {
        List<Volunteer> volunteerDTOs = volunteerServiceImp.getAllVolunteers() ;
        return new ResponseEntity<>(volunteerDTOs,HttpStatus.OK);

    }

    @PreAuthorize("hasRole('volunteer')")
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteVolunteerById(@PathVariable("id") Long id)
    {
        volunteerServiceImp.deleteVolunteerById(id);
        return new ResponseEntity<>("Deleted",HttpStatus.OK);
    }
    @PreAuthorize("hasRole('volunteer')")
    @PutMapping("{id}")
    public ResponseEntity<Volunteer> VolunteerDto(@RequestBody VolunteerDto volunteerDto,@PathVariable("id") long id)
    {
        Volunteer updatedvolunteerDto = volunteerServiceImp.updateVolunteer(volunteerDto,id);
        return new ResponseEntity<>(updatedvolunteerDto,HttpStatus.OK);
    }






}
