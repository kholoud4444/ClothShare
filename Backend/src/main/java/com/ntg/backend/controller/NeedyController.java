package com.ntg.backend.controller;

import com.ntg.backend.dto.requestDto.NeedyDto;
import com.ntg.backend.entity.Needy;
import com.ntg.backend.service.imp.NeedyServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/needy")
public class NeedyController {
    @Autowired
    private NeedyServiceImp needyServiceImp;

    //    @PostMapping
//    public ResponseEntity<Needy> addNeedy(@RequestBody NeedyDto needyDto) {
//        Needy savedNeedyDto = needyServiceImp.createNeedy(needyDto);
//        return new ResponseEntity<>(savedNeedyDto, HttpStatus.OK);
//    }
    @PreAuthorize("hasRole('ROLE_NEEDY')")
    @GetMapping("{id}")
    public ResponseEntity<Needy>getNeedyById(@PathVariable("id") long id )
    {
        Needy needy = needyServiceImp.getNeedyById(id);
        return new ResponseEntity<>(needy,HttpStatus.OK);

    }

    @PreAuthorize("hasRole('ROLE_NEEDY')")
    @GetMapping
    public ResponseEntity<List<Needy>>getAllNeedy()
    {
        List<Needy> NeedyDTOs = needyServiceImp.getAllNeedy() ;
        return new ResponseEntity<>(NeedyDTOs,HttpStatus.OK);
    }


    @PreAuthorize("hasRole('ROLE_NEEDY')")
    @PutMapping("{id}")
    public ResponseEntity<Needy> VolunteerDto(@RequestBody NeedyDto needyDto,@PathVariable("id") long id)
    {
        Needy updatedneedyDto = needyServiceImp.updateNeedy(needyDto,id);
        return new ResponseEntity<>(updatedneedyDto,HttpStatus.OK);
    }


}