package com.ntg.backend.controller;

import com.ntg.backend.dto.NeedyDto;
import com.ntg.backend.entity.Needy;
import com.ntg.backend.service.imp.NeedyServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/needy")
public class NeedyController {
    @Autowired
    private NeedyServiceImp needyServiceImp;
    public String hello() {
        return"hello";
    }
    @PostMapping
    public ResponseEntity<Needy> addNeedy(@RequestBody NeedyDto needyDto) {
        Needy savedNeedyDto = needyServiceImp.createNeedy(needyDto);
        return new ResponseEntity<>(savedNeedyDto, HttpStatus.OK);
    }
    @GetMapping("{id}")
    public ResponseEntity<Needy>getNeedyById(@PathVariable("id") long id )
    {
        Needy needy = needyServiceImp.getNeedyById(id);
        return new ResponseEntity<>(needy,HttpStatus.OK);

    }

    @GetMapping
    public ResponseEntity<List<Needy>>getAllNeedy()
    {
        List<Needy> NeedyDTOs = needyServiceImp.getAllNeedy() ;
        return new ResponseEntity<>(NeedyDTOs,HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteNeedyById(@PathVariable("id") Long id)
    {
        needyServiceImp.deleteNeedy(id);
        return new ResponseEntity<>("Deleted",HttpStatus.OK);
    }
    @PutMapping("{id}")
    public ResponseEntity<Needy> VolunteerDto(@RequestBody NeedyDto needyDto,@PathVariable("id") long id)
    {
        Needy updatedneedyDto = needyServiceImp.updateNeedy(needyDto,id);
        return new ResponseEntity<>(updatedneedyDto,HttpStatus.OK);
    }


}