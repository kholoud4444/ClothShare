package com.ntg.backend.controller;


import com.ntg.backend.dto.responseDto.RequestWithItemDetails;
import com.ntg.backend.dto.requestDto.RequestDto;
import com.ntg.backend.entity.Request;
import com.ntg.backend.service.imp.RequestServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/request")
public class RequestController {

    @Autowired
    private RequestServiceImp requestServiceImp;

    @PostMapping
    public ResponseEntity<RequestWithItemDetails> createRequest(@RequestBody RequestDto requestDto) {
        RequestWithItemDetails requestWithItemDetails = requestServiceImp.createRequest(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(requestWithItemDetails);
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
    @GetMapping("allRequestsByNeedyId/{id}")
    public ResponseEntity<List<Request>> getRequestByNeedyId(@PathVariable("id") Long id)
    {
        List<Request> Request = requestServiceImp.getAllRequestsByNeedyId(id) ;
        return new ResponseEntity<>(Request,HttpStatus.OK);
    }
    @GetMapping("/requestWithItemDetails/{id}")
    public ResponseEntity<RequestWithItemDetails>getRequestWithItemsDetails(@PathVariable("id") long id )
    {
        RequestWithItemDetails requestWithItemDetails = requestServiceImp.getRequestWithItemDetails(id);
        return new ResponseEntity<>(requestWithItemDetails,HttpStatus.OK);
    }
    @GetMapping("/AllRequestsByItemId/{id}")
    public ResponseEntity<List<RequestWithItemDetails>>getRequestWithItemsDetailsByItemId(@PathVariable("id") long id )
    {
       List <RequestWithItemDetails> requestWithItemDetails = requestServiceImp.getRequestsByItemId(id);
        return new ResponseEntity<>(requestWithItemDetails,HttpStatus.OK);
    }
}
