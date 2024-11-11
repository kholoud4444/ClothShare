package com.ntg.backend.controller;

import com.ntg.backend.dto.responseDto.RequestWithItemDetails;
import com.ntg.backend.service.imp.NeedyServiceImp;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/needy")
public class NeedyController {

    private final NeedyServiceImp needyServiceImp;

    public NeedyController(NeedyServiceImp needyServiceImp) {
        this.needyServiceImp = needyServiceImp;
    }

    //get all Requests with Item details for specific Needy
    @PreAuthorize("hasRole('ROLE_NEEDY')")
    @GetMapping("allRequestsDetails/{id}")
    public ResponseEntity<List<RequestWithItemDetails>> getAllRequestsDetailsByNeedyId(@PathVariable("id") long needyId)
    {
        List<RequestWithItemDetails> requestsWithItemDetailsByNeedyId = needyServiceImp.getAllRequestDetailsByNeedyId(needyId) ;
        return new ResponseEntity<>(requestsWithItemDetailsByNeedyId,HttpStatus.OK);
    }

    //get all Requests with Item details for all Needies
    @PreAuthorize("hasRole('ROLE_NEEDY')")
    @GetMapping("allRequestsDetails")
    public ResponseEntity<List<RequestWithItemDetails>> getAllRequestDetailsForAllNeedies() {
        List<RequestWithItemDetails> requests = needyServiceImp.getAllRequestDetailsForAllNeedies();
        return ResponseEntity.ok(requests);
    }


}