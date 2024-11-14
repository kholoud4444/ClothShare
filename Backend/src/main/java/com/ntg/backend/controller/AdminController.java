package com.ntg.backend.controller;

import com.ntg.backend.dto.ResponsePagination.PageDto;
import com.ntg.backend.dto.requestDto.ItemDto;
import com.ntg.backend.dto.requestDto.MessageDto;
import com.ntg.backend.dto.responseDto.ItemDetailsWithVolunteerName;
import com.ntg.backend.service.AdminService;
import com.ntg.backend.service.ItemService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController

@RequestMapping("/api/admin")
public class AdminController {


    private final AdminService adminService;
    private final ItemService itemServiceImp;

    public AdminController(AdminService adminService, ItemService itemService) {
        this.adminService = adminService;
        this.itemServiceImp = itemService;
    }

    @GetMapping("/allItems")
    public ResponseEntity<PageDto<ItemDetailsWithVolunteerName>> getAllItems
            (@RequestParam (value = "pageNo",defaultValue = "0",required = false) int pageNo,
             @RequestParam (value = "pageSize",defaultValue = "10",required = false) int pageSize)
    {
        PageDto<ItemDetailsWithVolunteerName> itemsDetailsWithVolunteerNameList
                = adminService.GetAllItemDetailsWithVolunteerNameList(pageNo,pageSize);
        return new ResponseEntity<>(itemsDetailsWithVolunteerNameList, HttpStatus.OK);
    }

//    @PutMapping("/changeItemStatus/{itemId}")
//    public  ResponseEntity<ItemDto> changeItemRequest(@PathVariable("itemId") long itemId, @RequestBody ItemDto itemDto)
//    {
//        ItemDto updatedItemDto = itemServiceImp.updateItem(itemDto, itemId);
//        return new ResponseEntity<>(updatedItemDto,HttpStatus.OK);
//    }
    @PutMapping("/changeItemStatus/{itemId}")
    public  ResponseEntity<MessageDto<ItemDto>> changeItemRequest(@PathVariable("itemId") long itemId, @RequestBody ItemDto ItemDto)
    {
        MessageDto<ItemDto> updateItemDto = itemServiceImp.changeItemStatus(ItemDto, itemId);
        return new ResponseEntity<>(updateItemDto,HttpStatus.OK);
    }
}
