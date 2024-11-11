package com.ntg.backend.controller;

import com.ntg.backend.dto.requestDto.ItemDto;
import com.ntg.backend.dto.responseDto.RequestWithNeedyDetails;
import com.ntg.backend.entity.Item;
import com.ntg.backend.service.imp.ItemServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/item")
public class ItemController {

    @Autowired
    private ItemServiceImp itemServiceImp;

    @PostMapping
    public ResponseEntity<Item> addItem(@RequestBody ItemDto itemDto) {
        Item savedItemDto = itemServiceImp.createItem(itemDto);
        return new ResponseEntity<>(savedItemDto, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Item>getItemById(@PathVariable("id") long id )
    {
        Item itemDto = itemServiceImp.getItemById(id);
        return new ResponseEntity<>(itemDto,HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Item>> getAllItems()
    {
        List<Item> ItemDTOs = itemServiceImp.getAllItems() ;
        return new ResponseEntity<>(ItemDTOs,HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteItemById(@PathVariable("id") Long id)
    {
        itemServiceImp.deleteItem(id);
        return new ResponseEntity<>("Deleted",HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<Item> VolunteerDto(@RequestBody ItemDto itemDto,@PathVariable("id") long id)
    {
        Item updatedItemDto = itemServiceImp.updateItem(itemDto,id);
        return new ResponseEntity<>(updatedItemDto,HttpStatus.OK);
    }
    @GetMapping("requestsWithNeedyDetails/{id}")
    public ResponseEntity<List<RequestWithNeedyDetails>> getAllRequestsNeedyDetails(@PathVariable("id") long itemId)
    {
        List<RequestWithNeedyDetails> requestWithNeedyDetails = itemServiceImp.requestWithNeedyDetails(itemId);
        return new ResponseEntity<>(requestWithNeedyDetails, HttpStatus.OK);
    }



}
