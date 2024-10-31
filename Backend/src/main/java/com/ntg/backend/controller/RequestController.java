package com.ntg.backend.controller;


import com.ntg.backend.dto.RequestDto;
import com.ntg.backend.entity.Request;
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

    @PostMapping("/{id}/{idd}")
    public ResponseEntity<Request> addRequest(@RequestBody RequestDto requestDto,
                                              @PathVariable("id") long idNeedy,
                                              @PathVariable("idd") long idItems) {
        Request savedRequest = requestServiceImp.createRequest(requestDto, idNeedy, idItems);
        return new ResponseEntity<>(savedRequest, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Request>getRequestById(@PathVariable("id") long id )
    {
        Request requestDto = requestServiceImp.getRequestById(id);
        return new ResponseEntity<>(requestDto,HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Request>> getAllRequests()
    {
        List<Request> RequestDTOs = requestServiceImp.getAllRequests() ;
        return new ResponseEntity<>(RequestDTOs,HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteRequestById(@PathVariable("id") Long id)
    {
        requestServiceImp.deleteRequest(id);
        return new ResponseEntity<>("Deleted",HttpStatus.OK);
    }

//    @PutMapping("{id}")
//    public ResponseEntity<Request> VolunteerDto(@RequestBody RequestDto requestDto, @PathVariable("id") long id)
//    {
//        RequestDto updatedRequestDto = requestServiceImp.updateRequest(requestDto,id);
//        return new ResponseEntity<>(updatedRequestDto,HttpStatus.OK);
//    }
}
