package com.ntg.backend.controller;

import com.ntg.backend.dto.ResponsePagination.PageDto;
import com.ntg.backend.dto.responseDto.RequestWithItemDetails;
import com.ntg.backend.service.imp.NeedyServiceImp;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/needy")
public class NeedyController {

    private final NeedyServiceImp needyServiceImp;

    public NeedyController(NeedyServiceImp needyServiceImp) {
        this.needyServiceImp = needyServiceImp;
    }


    //get all Requests with Item details for specific Needy
//    @PreAuthorize("hasRole('ROLE_NEEDY')")
    @GetMapping("/RequestsWithItemDetails/{id}")
    public ResponseEntity<PageDto<RequestWithItemDetails>> getAllRequestsDetailsByNeedyId
    (@PathVariable("id") long needyId,  @RequestParam (value = "pageNo",defaultValue = "0",required = false) int pageNo,
     @RequestParam (value = "pageSize",defaultValue = "10",required = false) int pageSize)
    {
        PageDto<RequestWithItemDetails> requestsWithItemDetailsByNeedyId = needyServiceImp
                .getAllRequestDetailsByNeedyId(needyId,pageNo,pageSize) ;
        return new ResponseEntity<>(requestsWithItemDetailsByNeedyId,HttpStatus.OK);
    }

    //get all Requests with Item details for all Needies
//    @PreAuthorize("hasRole('ROLE_NEEDY')")
    @GetMapping("/allRequestsDetailsWithItemDetails")
    public ResponseEntity<PageDto<RequestWithItemDetails>> getAllRequestDetailsForAllNeedies(
            @RequestParam (value = "pageNo",defaultValue = "0",required = false) int pageNo,
            @RequestParam (value = "pageSize",defaultValue = "10",required = false) int pageSize)
    {
        PageDto<RequestWithItemDetails> requests = needyServiceImp.getAllRequestDetailsForAllNeedies(pageNo, pageSize);
        return ResponseEntity.ok(requests);
    }

}