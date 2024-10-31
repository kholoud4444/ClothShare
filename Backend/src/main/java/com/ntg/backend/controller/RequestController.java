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

    @PostMapping
    public ResponseEntity<Request> createRequest(@RequestBody RequestDto requestDto) {
        Request createdRequest = requestServiceImp.createRequest(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdRequest);
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

    @PutMapping("/{id}")
    public ResponseEntity<Request> updateRequest(@PathVariable Long id, @RequestBody RequestDto requestDto) {
        // Call the service to update the request
        Request updatedRequest = requestServiceImp.updateRequest(requestDto, id);
        return ResponseEntity.ok(updatedRequest);
    }
}
