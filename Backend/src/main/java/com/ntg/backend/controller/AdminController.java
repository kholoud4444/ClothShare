package com.ntg.backend.controller;

import com.ntg.backend.dto.responseDto.ItemDetailsWithVolunteerName;
import com.ntg.backend.dto.responseDto.RequestWithItemDetails;
import com.ntg.backend.service.AdminService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

@RequestMapping("/api/admin")
public class AdminController {


    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping("/pending-items")
    public ResponseEntity< List<ItemDetailsWithVolunteerName>> getPendingItems() {
        List<ItemDetailsWithVolunteerName> itemsDetailsWithVolunteerNameList = adminService.GetAllItemDetailsWithVolunteerNameList();
        return new ResponseEntity<>(itemsDetailsWithVolunteerNameList, HttpStatus.OK);
    }

    @PutMapping("/approve/{id}")
    public  ResponseEntity< String> approveItem(@PathVariable("id") Long id) {
        adminService.ApproveItem(id);
        return new ResponseEntity<>("ItemApproved", HttpStatus.OK);
    }

    @PutMapping("/reject/{id}")
    public ResponseEntity< String>  rejectItem(@PathVariable("id") Long id) {
        adminService.RejectItem(id);
        return new ResponseEntity<>("ItemRejected", HttpStatus.OK);
    }
}
