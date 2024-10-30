package com.ntg.backend.controller;


import com.ntg.backend.dto.RequestDto;
import com.ntg.backend.service.imp.RequestServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/request")
public class RequestController {

    @Autowired
    private RequestServiceImp requestServiceImp;

    @PostMapping
    public ResponseEntity<RequestDto> addRequest(@RequestBody RequestDto requestDto) {
        RequestDto savedRequestDto = requestServiceImp.createRequest(requestDto);
        return new ResponseEntity<>(savedRequestDto, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<RequestDto>getRequestById(@PathVariable("id") long id )
    {
        RequestDto requestDto = requestServiceImp.getRequestById(id);
        return new ResponseEntity<>(requestDto,HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<RequestDto>> getAllRequests()
    {
        List<RequestDto> RequestDTOs = requestServiceImp.getAllRequests() ;
        return new ResponseEntity<>(RequestDTOs,HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteRequestById(@PathVariable("id") Long id)
    {
        requestServiceImp.deleteRequest(id);
        return new ResponseEntity<>("Deleted",HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<RequestDto> VolunteerDto(@RequestBody RequestDto requestDto,@PathVariable("id") long id)
    {
        RequestDto updatedRequestDto = requestServiceImp.updateRequest(requestDto,id);
        return new ResponseEntity<>(updatedRequestDto,HttpStatus.OK);
    }
}
