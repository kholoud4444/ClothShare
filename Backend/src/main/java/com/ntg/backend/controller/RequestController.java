package com.ntg.backend.controller;


import com.ntg.backend.dto.ResponsePagination.PageDto;
import com.ntg.backend.dto.requestDto.MessageDto;
import com.ntg.backend.dto.responseDto.RequestWithItemDetails;
import com.ntg.backend.dto.requestDto.RequestDto;
import com.ntg.backend.dto.responseDto.RequestWithNeedyDetails;
import com.ntg.backend.service.imp.RequestServiceImp;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/request")
public class RequestController {

    private final RequestServiceImp requestServiceImp;

    public RequestController(RequestServiceImp requestServiceImp) {
        this.requestServiceImp = requestServiceImp;
    }

    @PostMapping
    public ResponseEntity<RequestWithItemDetails> createRequest(@RequestBody RequestDto requestDto) {
        RequestWithItemDetails requestWithItemDetails = requestServiceImp.createRequest(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(requestWithItemDetails);
    }

    @GetMapping("{id}")
    public ResponseEntity<RequestDto>getRequestById(@PathVariable("id") long id )
    {
        RequestDto requestDto = requestServiceImp.getRequestById(id);
        return new ResponseEntity<>(requestDto,HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<PageDto<RequestDto>> getAllRequests(@RequestParam (value = "pageNo",defaultValue = "0",required = false) int pageNo,
                                                              @RequestParam (value = "pageSize",defaultValue = "10",required = false) int pageSize)
    {
        PageDto<RequestDto> RequestDTOs = requestServiceImp.getAllRequests(pageNo, pageSize) ;
        return new ResponseEntity<>(RequestDTOs,HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<MessageDto<String>> deleteRequestById(@PathVariable("id") Long id)
    {
        requestServiceImp.deleteRequest(id);
        return new ResponseEntity<>(new MessageDto<>("Request Deleted Successfully", null),HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RequestDto> updateRequest(@PathVariable Long id, @RequestBody RequestDto requestDto) {
        // Call the service to update the request
        RequestDto updatedRequest = requestServiceImp.updateRequest(requestDto, id);
        return ResponseEntity.ok(updatedRequest);
    }

    @GetMapping("/requestWithItemDetails/{id}")
    public ResponseEntity<RequestWithItemDetails>getRequestWithItemsDetails(@PathVariable("id") long id )
    {
        RequestWithItemDetails requestWithItemDetails = requestServiceImp.getRequestWithItemDetails(id);
        return new ResponseEntity<>(requestWithItemDetails,HttpStatus.OK);
    }

    @GetMapping("/AllRequestsByItemId/{id}")
    public ResponseEntity<PageDto<RequestWithItemDetails>>getRequestsWithItemsDetailsByItemId(@PathVariable("id") long id,
                                                                                          @RequestParam (value = "pageNo",defaultValue = "0",required = false) int pageNo,
                                                                                          @RequestParam (value = "pageSize",defaultValue = "10",required = false) int pageSize)
    {
        PageDto <RequestWithItemDetails> requestWithItemDetails = requestServiceImp.getRequestsByItemId(id, pageNo, pageSize);
        return new ResponseEntity<>(requestWithItemDetails,HttpStatus.OK);
    }

    @GetMapping("/requestsWithNeedyDetailsByItemId/{id}")
    public ResponseEntity<PageDto<RequestWithNeedyDetails>> getAllRequestsNeedyDetails(@PathVariable("id") long itemId,
                                                                                       @RequestParam (value = "pageNo",defaultValue = "0",required = false) int pageNo,
                                                                                       @RequestParam (value = "pageSize",defaultValue = "10",required = false) int pageSize)
    {
        PageDto<RequestWithNeedyDetails> requestWithNeedyDetails = requestServiceImp.requestsWithNeedyDetails(itemId, pageNo, pageSize);
        return new ResponseEntity<>(requestWithNeedyDetails, HttpStatus.OK);
    }
}
