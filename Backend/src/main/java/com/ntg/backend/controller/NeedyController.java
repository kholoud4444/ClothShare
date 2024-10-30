package com.ntg.backend.controller;

import com.ntg.backend.dto.NeedyDto;
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
    @PostMapping
    public ResponseEntity<NeedyDto> addNeedy(@RequestBody NeedyDto needyDto) {
        NeedyDto savedNeedyDto = needyServiceImp.createNeedy(needyDto);
        return new ResponseEntity<>(savedNeedyDto, HttpStatus.OK);
    }
    @GetMapping("{id}")
    public ResponseEntity<NeedyDto>getNeedyById(@PathVariable("id") long id )
    {
        NeedyDto needyDto = needyServiceImp.getNeedyById(id);
        return new ResponseEntity<>(needyDto,HttpStatus.OK);

    }

    @GetMapping
    public ResponseEntity<List<NeedyDto>>getAllNeedy()
    {
        List<NeedyDto> NeedyDTOs = needyServiceImp.getAllNeedy() ;
        return new ResponseEntity<>(NeedyDTOs,HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteNeedyById(@PathVariable("id") Long id)
    {
        needyServiceImp.deleteNeedy(id);
        return new ResponseEntity<>("Deleted",HttpStatus.OK);
    }
    @PutMapping("{id}")
    public ResponseEntity<NeedyDto> VolunteerDto(@RequestBody NeedyDto needyDto,@PathVariable("id") long id)
    {
        NeedyDto updatedneedyDto = needyServiceImp.updateNeedy(needyDto,id);
        return new ResponseEntity<>(updatedneedyDto,HttpStatus.OK);
    }


}